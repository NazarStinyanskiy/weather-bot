package ua.nazariy.weather.commands.slash;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.AbstractCommand;
import ua.nazariy.weather.db.connection.UserConnection;

public class WeatherStackCommand extends AbstractCommand {
    public WeatherStackCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        boolean result = UserConnection.updateWeatherService(user.getId(), "weatherstack");
        SendMessage message = getSendMessage(chat.getId());

        if(result){
            message.setText(language.getSpeech("service.successfully.changed"));
        } else {
            message.setText(language.getSpeech("internal.error"));
        }

        execute(absSender, message);
    }
}
