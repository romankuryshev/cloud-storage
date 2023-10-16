package cloud.storage.server.handler;

import cloud.storage.command.AbstractCommand;
import cloud.storage.command.MessageCommand;
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
        ctx.writeAndFlush(new MessageCommand("You are already logged in."));
    }
}
