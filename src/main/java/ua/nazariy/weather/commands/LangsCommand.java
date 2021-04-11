package ua.nazariy.weather.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.Settings;

public class LangsCommand extends AbstractCommand {
    public LangsCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage sendMessage = new SendMessage().setChatId(chat.getId());
        StringBuilder text = new StringBuilder();
        Settings.getLanguages().forEach((code, lang) -> text.append("/lang ").append(code).append(" - ").append(lang.getSpeech("language.full")).append('\n'));
        sendMessage.setText(text.toString());
        execute(absSender, sendMessage);
    }
}
