package cloud.storage.common.command;

import cloud.storage.common.enums.CommandName;
import cloud.storage.common.enums.Message;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class MessageCommand extends AbstractCommand {
    private String message;

    public MessageCommand() {
    }

    public MessageCommand(Message message) {
        commandName = CommandName.MESSAGE;
        this.message = message.getMessage();
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(commandName.ordinal());
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
