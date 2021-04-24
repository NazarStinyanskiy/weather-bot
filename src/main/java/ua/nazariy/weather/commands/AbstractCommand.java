package ua.nazariy.weather.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.nazariy.weather.Settings;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.db.pojo.UserPOJO;
import ua.nazariy.weather.lang.Language;

import java.util.Map;

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
