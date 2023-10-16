package cloud.storage.command;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Данный класс должны расширять все классы, которые описывают команды,
 * передаваемые между клиентом и сервером.
 */
public abstract class AbstractCommand implements Serializable {
    protected static final Charset charset = StandardCharsets.UTF_8;
    protected CommandName commandName;
    public CommandName getCommandName() {
        return commandName;
    }

    /**
     * Метод кодирует команду для передачи по сети.
     * @param buf ByteBuf в который передается команда.
     */
    public void encode(ByteBuf buf) {
        buf.writeInt(commandName.ordinal());
    }

    /**
     * Метод декодирует команду.
     * @param buf входящий ByteBuf
     * @return полученная команда.
     */
    public abstract AbstractCommand decode(ByteBuf buf);
}
