package ua.nazariy.weather.commands.slash;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.AbstractCommand;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.lang.Language;

public class LanguageCommand extends AbstractCommand {

    public LanguageCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String param = concatArgs(strings);
        SendMessage sendMessage = getSendMessage(chat.getId());

        if(param.length() == 0){
            sendMessage.setText(language.getSpeech("no.such.language"));
            execute(absSender, sendMessage);
            return;
        }

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
