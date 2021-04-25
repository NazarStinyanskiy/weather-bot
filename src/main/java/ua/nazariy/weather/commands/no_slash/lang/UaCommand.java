package ua.nazariy.weather.commands.no_slash.lang;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.no_slash.AbstractNoSlashCommand;

public class UaCommand extends AbstractNoSlashCommand {
    public UaCommand(String identifier, String state) {
        super(identifier, state);
    }

    @Override
    protected void execute(Update update, AbsSender absSender) {

    }
}
