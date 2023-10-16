package cloud.storage.server.service;

import cloud.storage.server.entity.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationService {
    private static final HashMap<String, User> users = new HashMap<>();
    private static final Set<Channel> authenticatedChannels = new HashSet<>();

    public static boolean authenticate(String username, String password, ChannelHandlerContext ctx) {
        if (!users.containsKey(username)) {
            return false;
        }
        User currentUser = users.get(username);

        if (currentUser.isBlocked()) {
            return false;
        }

        if (currentUser.getPassword().equals(password)) {
            authenticatedChannels.add(ctx.channel());
            return true;
        } else {
            currentUser.reduceAttemptsCount();
            return false;
        }
    }

    public synchronized static boolean isChannelAuthenticated(Channel channel) {
        return authenticatedChannels.contains(channel);
    }

    public synchronized static boolean addNewUser(String username, String password) {
        System.out.println(username + " " + password);
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password, false, User.DEFAULT_ATTEMPTS_COUNT));
        return true;
    }

    public synchronized static void removeFromAuthenticatedChannels(Channel channel) {
        authenticatedChannels.remove(channel);
    }

}
