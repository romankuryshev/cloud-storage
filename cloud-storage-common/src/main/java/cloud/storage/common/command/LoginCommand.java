package cloud.storage.common.command;

import io.netty.buffer.ByteBuf;

public class LoginCommand extends AbstractCommand{
    public String username;
    public String password;

    public LoginCommand() {
        this.commandName = CommandName.LOGIN;
    }

    public LoginCommand(String username, String password) {
        this.commandName = CommandName.LOGIN;
        this.username = username;
        this.password = password;
    }

    @Override
    public void encode(ByteBuf buf) {
        super.encode(buf);
        buf.writeInt(username.length());
        buf.writeCharSequence(username, charset);
        buf.writeInt(password.length());
        buf.writeCharSequence(password, charset);
    }

    @Override
    public AbstractCommand decode(ByteBuf buf) {
        int usernameLength = buf.readInt();
        username = buf.readCharSequence(usernameLength,charset).toString();
        int passwordLength = buf.readInt();
        password = buf.readCharSequence(passwordLength, charset).toString();
        return this;
    }
}
