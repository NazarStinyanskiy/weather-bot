package ua.nazariy.weather.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.lang.Language;

public class LanguageCommand extends AbstractCommand{

    public LanguageCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String param = concatArgs(strings);
        SendMessage sendMessage = new SendMessage().setChatId(chat.getId());
        Language chosenLanguage = defineLanguage(param);

        if(chosenLanguage == null){
            sendMessage.setText(language.getSpeech("no.such.language"));
        } else {
            if(UserConnection.updateLanguage(user.getId(), param)){
                language = chosenLanguage;
                sendMessage.setText(language.getSpeech("language.successfully.changed"));
            } else {
                sendMessage.setText(language.getSpeech("internal.error"));
            }
        }

        execute(absSender, sendMessage);
    }
}
