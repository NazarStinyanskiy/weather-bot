package ua.nazariy.weather.config.parsers.json;

import ua.nazariy.weather.config.parsers.Parser;
import ua.nazariy.weather.models.Model;

public abstract class JSONParser implements Parser {
    public abstract Model parse(String json);
}
