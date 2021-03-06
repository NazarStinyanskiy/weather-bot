package ua.nazariy.weather.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.lang.Language;

public abstract class AbstractCommand extends BotCommand implements Command{
    protected Language language;

    public AbstractCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        User from = message.getFrom();
        writeUserIntoDB(from.getId());
        language = defineLanguage(from);
        super.processMessage(absSender, message, arguments);
    }

    protected String concatArgs(String[] arguments){
        return concatArgs(arguments, false);
    }

    protected String concatArgs(String[] arguments, boolean withSpaces){
        StringBuilder param = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            param.append(arguments[i]);
            if(withSpaces && i < arguments.length - 1){
                param.append(' ');
            }
        }
        return param.toString();
    }
}
