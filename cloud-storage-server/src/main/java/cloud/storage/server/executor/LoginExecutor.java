package cloud.storage.server.executor;

import cloud.storage.common.command.AbstractCommand;
import cloud.storage.common.command.LoginCommand;
import cloud.storage.common.command.MessageCommand;
import cloud.storage.common.enums.Message;
import cloud.storage.server.service.AuthenticationService;
import io.netty.channel.ChannelHandlerContext;

/**
 * Обработчик команды LoginCommand.
 */
public class LoginExecutor implements CommandExecutor {
    @Override
    public void execute(ChannelHandlerContext ctx, AbstractCommand command) {
        LoginCommand currentCommand = (LoginCommand) command;
        if (AuthenticationService.authenticate(currentCommand.getUsername(), currentCommand.getPassword(), ctx)) {
            ctx.writeAndFlush(new MessageCommand(Message.LOGIN_SUCCESS));
        } else {
            ctx.writeAndFlush(new MessageCommand(Message.LOGIN_ERROR));
        }
    }
}
