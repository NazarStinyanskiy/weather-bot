package ua.nazariy.weather.commands.no_slash.lang;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.no_slash.AbstractNoSlashCommand;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.lang.Language;

public class LangCommand extends AbstractNoSlashCommand {
    protected String LANG;

    public LangCommand(String identifier, String state) {
        super(identifier, state);
    }

    @Override
    protected void execute(Update update, AbsSender absSender) {
        User from = update.getMessage().getFrom();
        SendMessage sendMessage = getSendMessage(from.getId());
        Language chosenLanguage = defineLanguage(LANG);

        if(chosenLanguage == null){
            sendMessage.setText(language.getSpeech("no.such.language"));
        } else {
            if(UserConnection.updateLanguage(from.getId(), LANG)){
                language = chosenLanguage;
                sendMessage.setText(language.getSpeech("language.successfully.changed"));
            } else {
                sendMessage.setText(language.getSpeech("internal.error"));
            }
        }

        execute(absSender, sendMessage);
    }
}
