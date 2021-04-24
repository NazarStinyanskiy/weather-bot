package ua.nazariy.weather.commands.no_slash;

import java.util.HashMap;
import java.util.Map;

public class NoSlashCommandStorage {
    private static Map<String, AbstractNoSlashCommand> commands = new HashMap<>();

    public static void register(AbstractNoSlashCommand command){
        commands.put(command.getIdentifier(), command);
    }

    public static AbstractNoSlashCommand getCommand(String identifier){
        return commands.get(identifier);
    }
}
