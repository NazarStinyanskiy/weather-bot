package ua.nazariy.weather.commands.slash;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.AbstractCommand;

public class StartCommand extends AbstractCommand {

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        execute(absSender, getSendMessage(chat.getId(), language.getSpeech("start.greetings")));
    }
}
