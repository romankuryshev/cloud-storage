package cloud.storage.command.codec;

import cloud.storage.command.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.HashMap;
import java.util.List;

public class CommandDecoder extends ReplayingDecoder<Void> {
    private final HashMap<CommandName, AbstractCommand> commands;

    public CommandDecoder() {
        commands = new HashMap<>();
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.MESSAGE, new MessageCommand());
    }

    /**
     * Метод декодирует команду.
     * @param ctx           the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in            the {@link ByteBuf} from which to read data
     * @param out           the {@link List} to which decoded messages should be added
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        CommandName commandName = CommandName.values()[in.readInt()];
        AbstractCommand currentCommand = commands.get(commandName);
        out.add(currentCommand.decode(in));
    }
}
