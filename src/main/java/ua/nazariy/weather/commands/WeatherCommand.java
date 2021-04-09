package ua.nazariy.weather.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.config.services.AbstractWeatherService;
import ua.nazariy.weather.config.services.ServiceStorage;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.db.pojo.UserPOJO;
import ua.nazariy.weather.models.open_weather_map.OpenWeatherMapModel;

import java.util.HashMap;
import java.util.Map;

public class WeatherCommand extends AbstractCommand{
    public WeatherCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        UserPOJO userPOJO = UserConnection.select(user.getId());
        SendMessage message = new SendMessage().setChatId(chat.getId());

        if(userPOJO.getWeatherService() == null){
            message.setText(language.NO_SERVICE_CHOSEN);
            execute(absSender, message);
            return;
        }

        AbstractWeatherService service = ServiceStorage.getFactory(userPOJO.getWeatherService());
        if(service == null){
            message.setText(language.NO_SUCH_SERVICE);
            execute(absSender, message);
            return;
        }
        Map<String, String> parameters = new HashMap<>();
        if (strings.length == 2) {
            parameters.put("city", strings[0]);
            parameters.put("country", strings[1]);
        } else if (strings.length == 1) {
            parameters.put("city", strings[0]);
        }

        parameters.put("language", language.LANGUAGE_SHORT);
        service.setup(parameters);

        if(((OpenWeatherMapModel) service.getWeather()).getCod() == 400){
            message.setText(language.NO_CITY_ENTERED);
        } else if(((OpenWeatherMapModel) service.getWeather()).getCod() == 404){
            message.setText(language.CITY_NOT_FOUND);
        } else if(((OpenWeatherMapModel) service.getWeather()).getCod() == 200){
            message.setText(createAnswer(service));
        } else {
            message.setText(language.INTERNAL_ERROR);
        }

        execute(absSender, message);
    }

    private String createAnswer(AbstractWeatherService service){
        OpenWeatherMapModel model = (OpenWeatherMapModel) service.getWeather();
        return language.TEMPERATURE + model.getMain().getTemp() + '\n' +
                language.FEELS_LIKE + model.getMain().getFeels_like() + '\n' +
                model.getWeather()[0].getDescription();
    }
}
