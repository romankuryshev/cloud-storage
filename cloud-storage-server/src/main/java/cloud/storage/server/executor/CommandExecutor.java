package cloud.storage.server.executor;

import cloud.storage.common.command.AbstractCommand;
import io.netty.channel.ChannelHandlerContext;

/**
 * Обработчик команд.
 * Каждый класс, который расширяется класс {@code AbstractCommand} должен иметь
 * собственную реализацию данного интерфейса.
 * @see AbstractCommand
 */
public interface CommandExecutor {
    /**
     * Метод содержит логику обработки команды.
     * @param ctx текущий ChannelHandlerContext.
     * @param command команда, которая должна быть обработана.
     */
    void execute(ChannelHandlerContext ctx, AbstractCommand command);
}
