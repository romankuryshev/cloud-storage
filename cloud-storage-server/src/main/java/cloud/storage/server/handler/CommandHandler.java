package cloud.storage.server.handler;

import cloud.storage.common.command.AbstractCommand;
import cloud.storage.common.command.MessageCommand;
import cloud.storage.common.enums.Message;
import cloud.storage.server.service.CommandService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Хендлер входящих команд. В него попадают только команды от уже
 * аутентифицированных пользователей.
 */
public class CommandHandler extends SimpleChannelInboundHandler<AbstractCommand> {
    private static final CommandService commandService = new CommandService();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractCommand command) throws Exception {
        ctx.writeAndFlush(new MessageCommand(Message.LOGIN_ALREADY));
    }
}
