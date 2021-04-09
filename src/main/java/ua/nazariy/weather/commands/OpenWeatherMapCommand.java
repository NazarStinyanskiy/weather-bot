package ua.nazariy.weather.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.db.connection.UserConnection;

public class OpenWeatherMapCommand extends AbstractCommand{
    public OpenWeatherMapCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        boolean result = UserConnection.updateWeatherService(user.getId(), "open_weather_map");
        SendMessage message = new SendMessage().setChatId(chat.getId());

        if(result){
            message.setText(language.SERVICE_SUCCESSFULLY_CHANGED);
        } else {
            message.setText(language.INTERNAL_ERROR);
        }

        execute(absSender, message);
    }
}
