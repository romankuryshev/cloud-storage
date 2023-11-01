package cloud.storage.common.command;

import cloud.storage.common.enums.CommandName;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class RegisterCommand extends AbstractCommand{
    private String username;
    private String password;

    public RegisterCommand() {
        this.commandName = CommandName.REGISTER;
    }

    public RegisterCommand(String username, String password) {
        this.commandName = CommandName.REGISTER;
        this.username = username;
        this.password = password;
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(commandName.ordinal());
        buf.writeInt(username.length());
        buf.writeCharSequence(username, charset);
        buf.writeInt(password.length());
        buf.writeCharSequence(password, charset);
    }

    @Override
    public AbstractCommand decode(ByteBuf buf) {
        int usernameLength = buf.readInt();
        username = buf.readCharSequence(usernameLength, charset).toString();
        int passwordLength = buf.readInt();
        password = buf.readCharSequence(passwordLength, charset).toString();
        return this;
    }
}
