package cloud.storage.server.executor;

import cloud.storage.common.command.AbstractCommand;
import cloud.storage.common.command.MessageCommand;
import io.netty.channel.ChannelHandlerContext;

/**
 * Обработчик команды MessageCommand.
 */
public class MessageExecutor implements CommandExecutor{
    @Override
    public void execute(ChannelHandlerContext ctx, AbstractCommand command) {
        ctx.writeAndFlush(((MessageCommand) command).getMessage());
    }
}
