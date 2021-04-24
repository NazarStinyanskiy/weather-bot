package ua.nazariy.weather.commands.slash;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.Settings;
import ua.nazariy.weather.commands.AbstractCommand;

public class LangsCommand extends AbstractCommand {
    public LangsCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        StringBuilder text = new StringBuilder();
        Settings.getLanguages().forEach((code, lang) -> text.append("/lang ").append(code).append(" - ").append(lang.getSpeech("language.full")).append('\n'));
        execute(absSender, getSendMessage(chat.getId(), text.toString()));
    }
}
