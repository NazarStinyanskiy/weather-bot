package ua.nazariy.weather.config.parsers.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.nazariy.weather.models.weatherstack.WeatherStackModel;

public class WeatherStackParserJSON extends JSONParser{
    @Override
    public WeatherStackModel parse(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, WeatherStackModel.class);
    }
}
