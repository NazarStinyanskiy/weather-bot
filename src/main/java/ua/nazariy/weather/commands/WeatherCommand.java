package ua.nazariy.weather.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.config.services.AbstractWeatherService;
import ua.nazariy.weather.config.services.ServiceStorage;
import ua.nazariy.weather.config.services.exception.CityNotFoundException;
import ua.nazariy.weather.config.services.exception.StatusCodeException;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.db.pojo.UserPOJO;
import ua.nazariy.weather.models.Model;

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

        if(strings.length == 0){
            message.setText(language.NO_CITY_ENTERED);
            execute(absSender, message);
            return;
        }

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

        service.setup(createParameters(strings));
        setupMessage(message, service);
        execute(absSender, message);
    }

    private Map<String, String> createParameters(String[] strings){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("city", concatArgs(strings, true));
        parameters.put("language", language.LANGUAGE_SHORT);
        parameters.put("units", "metric");
        return parameters;
    }

    private SendMessage setupMessage(SendMessage message, AbstractWeatherService service){
        try {
            Model model = service.getModel();
            message.setText(createAnswer(model));
        } catch (CityNotFoundException e) {
            message.setText(language.CITY_NOT_FOUND);
        } catch (StatusCodeException e) {
            message.setText(language.INTERNAL_ERROR);
        }

        return message;
    }

    private String createAnswer(Model model){
        StringBuilder answer = new StringBuilder();
        if(model.getTemperature() != null){
            answer.append(language.TEMPERATURE).append(formattedTemperature(model.getTemperature())).append('\n');
        }

        if(model.getFeelsLikeTemperature() != null){
            answer.append(language.FEELS_LIKE).append(formattedTemperature(model.getFeelsLikeTemperature())).append('\n');
        }

        if(model.getDescription() != null){
            answer.append(model.getDescription());
        }
        return answer.toString();
    }

    private String formattedTemperature(double temp){
        return temp >= 0 ? "+" + Math.round(temp) : Math.round(temp) + "";
    }
}
