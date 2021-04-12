package ua.nazariy.weather.config.services;

import ua.nazariy.weather.Settings;
import ua.nazariy.weather.config.connection.HTTPConnection;
import ua.nazariy.weather.config.parsers.json.OpenWeatherMapJSONParser;
import ua.nazariy.weather.config.services.exception.CityNotFoundException;
import ua.nazariy.weather.config.services.exception.StatusCodeException;
import ua.nazariy.weather.models.open_weather_map.OpenWeatherMapModel;

import java.util.Map;

public class OpenWeatherMapService extends AbstractWeatherService {
    private String url;

    public OpenWeatherMapService(){
        parser = new OpenWeatherMapJSONParser();
    }

    @Override
    public void setup(Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder();
        StringBuilder qBuilder = new StringBuilder();
        urlBuilder.append(Settings.getConfig().getProperty("open.weather.map.url"));

        if (params.containsKey("city")) {
            qBuilder.append(params.get("city"));
        }

        if (params.containsKey("country")) {
            qBuilder.append(',').append(params.get("country"));
        }

        if (!qBuilder.isEmpty()) {
            urlBuilder.append("q=").append(qBuilder).append("&");
        }

        if (params.containsKey("language")) {
            urlBuilder.append("lang=").append(params.get("language")).append("&");
        }

        if (params.containsKey("units")) {
            urlBuilder.append("units=").append(params.get("units")).append("&");
        }

        urlBuilder.append("appid=").append(Settings.getSecureConfig().getProperty("open.weather.map.appid"));
        url = urlBuilder.toString().replaceAll(" ", "%20");
    }

    @Override
    public OpenWeatherMapModel getModel() throws CityNotFoundException, StatusCodeException {
        HTTPConnection connection = new HTTPConnection();
        connection.request(url);
        if (connection.response().statusCode() == 200) {
            String json = connection.response().body();
            return (OpenWeatherMapModel) parser.parse(json);
        } else if (connection.response().statusCode() == 404) {
            throw new CityNotFoundException();
        } else {
            throw new StatusCodeException();
        }
    }
}
