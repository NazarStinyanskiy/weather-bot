package ua.nazariy.weather.config.parsers.json;

import ua.nazariy.weather.models.Model;

public abstract class JSONParser {
    protected abstract Model parse(String json);
}
