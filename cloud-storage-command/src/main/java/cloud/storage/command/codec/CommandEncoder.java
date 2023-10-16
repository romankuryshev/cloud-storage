package cloud.storage.command.codec;

import cloud.storage.command.AbstractCommand;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CommandEncoder extends MessageToByteEncoder<AbstractCommand> {

    /**
     * Метод кодирует команду.
     * @param ctx           the {@link ChannelHandlerContext} which this {@link MessageToByteEncoder} belongs to
     * @param msg           the message to encode
     * @param out           the {@link ByteBuf} into which the encoded message will be written
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx,
                          AbstractCommand msg, ByteBuf out) throws Exception {
        msg.encode(out);
    }
}
