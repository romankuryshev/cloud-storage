package cloud.storage.server.executor;

import cloud.storage.common.command.AbstractCommand;
import cloud.storage.common.command.LoginCommand;
import cloud.storage.common.command.MessageCommand;
import cloud.storage.server.service.AuthenticationService;
import io.netty.channel.ChannelHandlerContext;

/**
 * Обработчик команды LoginCommand.
 */
public class LoginExecutor implements CommandExecutor {
    @Override
    public void execute(ChannelHandlerContext ctx, AbstractCommand command) {
        LoginCommand currentCommand = (LoginCommand) command;
        if (AuthenticationService.authenticate(currentCommand.username, currentCommand.password, ctx)) {
            ctx.writeAndFlush(new MessageCommand("You have successfully logged in!"));
        } else {
            ctx.writeAndFlush(new MessageCommand("Incorrect login or password."));
        }
    }
}
