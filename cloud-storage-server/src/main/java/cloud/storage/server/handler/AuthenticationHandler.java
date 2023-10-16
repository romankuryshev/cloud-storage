package cloud.storage.server.handler;

import cloud.storage.command.AbstractCommand;
import cloud.storage.server.executor.CommandExecutor;
import cloud.storage.server.service.AuthenticationService;
import cloud.storage.server.service.CommandService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

/**
 * Хендлер обрабатывает запросы на регистрацию и аутентификацию.
 * Запросы от уже аутентифицированных пользователей пробрасываются дальше.
 */
public class AuthenticationHandler extends SimpleChannelInboundHandler<AbstractCommand> {
    private static final CommandService commandService = new CommandService();
    private final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractCommand command) throws Exception {
        if (AuthenticationService.isChannelAuthenticated(ctx.channel())) {
            logger.info("User was authenticated.");
            ctx.fireChannelRead(command);
        } else {
            logger.info("Authentication.");
            CommandExecutor authCommand = commandService.getAuthCommandExecutor(command.getCommandName());
            authCommand.execute(ctx, command);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("User disconnected.");
        AuthenticationService.removeFromAuthenticatedChannels(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(Arrays.toString(cause.getStackTrace()));
        AuthenticationService.removeFromAuthenticatedChannels(ctx.channel());
        ctx.close();
    }
}
