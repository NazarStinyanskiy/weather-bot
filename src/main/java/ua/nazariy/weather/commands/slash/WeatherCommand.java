package ua.nazariy.weather.commands.slash;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.AbstractCommand;
import ua.nazariy.weather.config.services.AbstractWeatherService;
import ua.nazariy.weather.config.services.ServiceStorage;
import ua.nazariy.weather.config.services.exception.CityNotFoundException;
import ua.nazariy.weather.config.services.exception.StatusCodeException;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.db.pojo.UserModel;
import ua.nazariy.weather.models.Model;

import java.util.HashMap;
import java.util.Map;

public class WeatherCommand extends AbstractCommand {
    public WeatherCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        UserModel userModel = UserConnection.select(user.getId());
        SendMessage message = getSendMessage(chat.getId());

        if (strings.length == 0) {
            message.setText(language.getSpeech("no.city.entered"));
            execute(absSender, message);
            return;
        }

        if (userModel.getWeatherService() == null) {
            message.setText(language.getSpeech("no.service.chosen"));
            execute(absSender, message);
            return;
        }

        AbstractWeatherService service = ServiceStorage.getService(userModel.getWeatherService());
        if (service == null) {
            message.setText(language.getSpeech("no.such.service"));
            execute(absSender, message);
            return;
        }

        service.setup(createParameters(strings));
        setupMessage(message, service);
        execute(absSender, message);
    }

    private Map<String, String> createParameters(String[] strings) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("city", concatArgs(strings, true));
        parameters.put("language", language.getSpeech("language.short"));
        parameters.put("units", "metric");
        return parameters;
    }

    private void setupMessage(SendMessage message, AbstractWeatherService service) {
        try {
            Model model = service.getModel();
            message.setText(createAnswer(model));
        } catch (CityNotFoundException e) {
            message.setText(language.getSpeech("city.not.found"));
        } catch (StatusCodeException e) {
            message.setText(language.getSpeech("internal.error"));
        }
    }

    private String createAnswer(Model model) {
        StringBuilder answer = new StringBuilder();

        if(model.getPlaceName() != null){
            answer.append(language.getSpeech("info.for")).append(" ").append(model.getPlaceName()).append('\n');
        }

        if (model.getTemperature() != null) {
            answer.append(language.getSpeech("temperature")).append(" ").append(formattedTemperature(model.getTemperature())).append('\n');
        }

        if (model.getFeelsLikeTemperature() != null) {
            answer.append(language.getSpeech("feels.like")).append(" ").append(formattedTemperature(model.getFeelsLikeTemperature())).append('\n');
        }

        if (model.getDescription() != null) {
            answer.append(model.getDescription());
        }
        return answer.toString();
    }

    private String formattedTemperature(double temp) {
        return temp >= 0 ? "+" + Math.round(temp) : Math.round(temp) + "";
    }
}
