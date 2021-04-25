package ua.nazariy.weather.config.services;

import ua.nazariy.weather.Settings;
import ua.nazariy.weather.config.connection.HTTPConnection;
import ua.nazariy.weather.config.parsers.json.WeatherStackParserJSON;
import ua.nazariy.weather.config.services.exception.CityNotFoundException;
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
    public WeatherStackModel getModel() throws CityNotFoundException {
        HTTPConnection connection = new HTTPConnection();
        connection.request(url);
        String json = connection.response().body();
        WeatherStackModel model = (WeatherStackModel) parser.parse(json);
        if (model != null && model.getError() != null) {
            throw new CityNotFoundException();
        }

        return model;
    }
}
