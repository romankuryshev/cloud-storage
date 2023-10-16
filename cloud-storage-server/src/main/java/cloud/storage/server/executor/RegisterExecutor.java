package cloud.storage.server.executor;

import cloud.storage.command.AbstractCommand;
import cloud.storage.command.MessageCommand;
import cloud.storage.command.RegisterCommand;
import cloud.storage.server.service.AuthenticationService;
import io.netty.channel.ChannelHandlerContext;

/**
 * Обработчик команды RegisterCommand.
 */
public class RegisterExecutor implements CommandExecutor {
    @Override
    public void execute(ChannelHandlerContext ctx, AbstractCommand command) {
        RegisterCommand currentCommand = (RegisterCommand) command;
        if (AuthenticationService.addNewUser(currentCommand.getUsername(), currentCommand.getPassword())) {
            ctx.writeAndFlush(new MessageCommand("You registered!"));
        } else {
            ctx.writeAndFlush(new MessageCommand("A user with the same name already exists."));
        }
    }
}
