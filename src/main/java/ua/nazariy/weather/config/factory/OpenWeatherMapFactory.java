package ua.nazariy.weather.config.factory;

import ua.nazariy.weather.config.connection.HTTPConnection;
import ua.nazariy.weather.models.open_weather_map.OpenWeatherMapModel;

public class OpenWeatherMapFactory extends AbstractFactory {
    @Override
    public OpenWeatherMapModel getModel() {
        HTTPConnection connection = new HTTPConnection();
        connection.request("");
        return new OpenWeatherMapModel();
    }
}
