package ua.nazariy.weather.commands.no_slash;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.Command;
import ua.nazariy.weather.config.services.Services;
import ua.nazariy.weather.db.connection.UserConnection;

public class OpenWeatherMapCommand extends AbstractNoSlashCommand implements Command {

    public OpenWeatherMapCommand(String command, String state) {
        super(command, state);
    }

    @Override
    protected void execute(Update update, AbsSender absSender) {
        User from = update.getMessage().getFrom();

        boolean result = UserConnection.updateWeatherService(from.getId(), Services.OPEN_WEATHER_MAP);
        SendMessage message = getSendMessage(update.getMessage().getChat().getId());

        if(result){
            message.setText(language.getSpeech("service.successfully.changed"));
        } else {
            message.setText(language.getSpeech("internal.error"));
        }

        execute(absSender, message);
    }
}
