package ua.nazariy.weather.config.services;

import ua.nazariy.weather.Settings;
import ua.nazariy.weather.config.connection.HTTPConnection;
import ua.nazariy.weather.config.parsers.json.WeatherStackParserJSON;
import ua.nazariy.weather.config.services.exception.CityNotFoundException;
import ua.nazariy.weather.config.services.exception.StatusCodeException;
import ua.nazariy.weather.models.Model;
import ua.nazariy.weather.models.open_weather_map.OpenWeatherMapModel;
import ua.nazariy.weather.models.weatherstack.WeatherStackModel;

import java.util.Map;

public class WeatherStackService extends AbstractWeatherService{
    private String url;

    public WeatherStackService() {
        parser = new WeatherStackParserJSON();
    }

    @Override
    public void setup(Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder();
        StringBuilder qBuilder = new StringBuilder();
        urlBuilder.append(Settings.getConfig().getProperty("weatherstack.url"));

        if (params.containsKey("city")) {
            qBuilder.append(params.get("city"));
        }

        if (!qBuilder.isEmpty()) {
            urlBuilder.append("query=").append(qBuilder).append("&");
        }

        urlBuilder.append("access_key=").append(Settings.getSecureConfig().getProperty("weatherstack.access.key"));
        url = urlBuilder.toString().replaceAll(" ", "%20");
    }

    @Override
    public WeatherStackModel getModel() throws CityNotFoundException, StatusCodeException {
        HTTPConnection connection = new HTTPConnection();
        connection.request(url);
        if (connection.response().statusCode() == 200) {
            String json = connection.response().body();
            return (WeatherStackModel) parser.parse(json);
        } else if (connection.response().statusCode() == 404) {
            throw new CityNotFoundException();
        } else {
            throw new StatusCodeException();
        }
    }
}