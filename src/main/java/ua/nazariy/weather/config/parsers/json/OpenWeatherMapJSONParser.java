package ua.nazariy.weather.config.parsers.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.nazariy.weather.models.open_weather_map.OpenWeatherMapModel;

public class OpenWeatherMapJSONParser extends JSONParser {
    @Override
    public OpenWeatherMapModel parse(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, OpenWeatherMapModel.class);
    }
}
