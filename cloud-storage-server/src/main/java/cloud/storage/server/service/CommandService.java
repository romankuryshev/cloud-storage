package cloud.storage.server.service;

import cloud.storage.common.enums.CommandName;
import cloud.storage.server.executor.*;
import java.util.EnumMap;

/**
 * Класс для получения обработчиков команд.
 */
public class CommandService {
    private static EnumMap<CommandName, CommandExecutor> commandExecutors;
    private static EnumMap<CommandName, CommandExecutor> authCommandExecutors;

    public CommandService() {
        commandExecutors = new EnumMap<>(CommandName.class);
        commandExecutors.put(CommandName.MESSAGE, new MessageExecutor());

        authCommandExecutors = new EnumMap<>(CommandName.class);
        authCommandExecutors.put(CommandName.LOGIN, new LoginExecutor());
        authCommandExecutors.put(CommandName.REGISTER, new RegisterExecutor());
    }

    /**
     * Метод возвращает обработчик команды.
     * @param commandName имя команды.
     * @return обработчик.
     */
    public CommandExecutor getCommandExecutor(CommandName commandName) {
        return commandExecutors.get(commandName);
    }

    /**
     * Метод возвращает обработчик команды аутентификации.
     * @param commandName название команды.
     * @return обработчик.
     */
    public CommandExecutor getAuthCommandExecutor(CommandName commandName) {
        return authCommandExecutors.get(commandName);
    }
}
