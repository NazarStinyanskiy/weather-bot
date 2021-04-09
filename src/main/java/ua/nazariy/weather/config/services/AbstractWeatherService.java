package ua.nazariy.weather.config.services;

import ua.nazariy.weather.config.parsers.json.JSONParser;
import ua.nazariy.weather.models.Model;

import java.util.Map;

public abstract class AbstractWeatherService {
    protected JSONParser parser;

    public abstract void setup(Map<String, String> params);

    public abstract Model getWeather();
}
