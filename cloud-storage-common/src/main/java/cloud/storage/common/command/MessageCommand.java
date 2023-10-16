package cloud.storage.common.command;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class MessageCommand extends AbstractCommand {
    private String message;

    public MessageCommand() {
        commandName = CommandName.MESSAGE;
    }

    public MessageCommand(String message) {
        commandName = CommandName.MESSAGE;
        this.message = message;
    }

    @Override
    public void encode(ByteBuf buf) {
        super.encode(buf);
        buf.writeInt(message.length());
        buf.writeCharSequence(message, charset);
    }

    @Override
    public AbstractCommand decode(ByteBuf buf) {
        int messageLength = buf.readInt();
        message = buf.readCharSequence(messageLength, charset).toString();
        return this;
    }
}
