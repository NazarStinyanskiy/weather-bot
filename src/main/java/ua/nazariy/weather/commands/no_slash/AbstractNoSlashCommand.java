package ua.nazariy.weather.commands.no_slash;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.Command;
import ua.nazariy.weather.lang.Language;

public abstract class AbstractNoSlashCommand implements Command {
    protected String identifier;
    protected String state;

    protected Language language;

    public AbstractNoSlashCommand(String identifier, String state){
        this.identifier = identifier;
        this.state = state;
    }

    public final void processMessage(Update update, AbsSender absSender){
        // if user-state equals this.state -> process message
        User from = update.getMessage().getFrom();
        writeUserIntoDB(from.getId());
        if(!validateUserState(from.getId(), state)){
            return;
        }

        language = defineLanguage(from);

        execute(update, absSender);
    }

    protected abstract void execute(Update update, AbsSender absSender);

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
