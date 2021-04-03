package ua.nazariy.weather.models.open_weather_map;

import ua.nazariy.weather.models.Model;

public class OpenWeatherMapModel implements Model {
    private Coord coordModel;
    private Weather[] weather;
    private Clouds cloudsModel;
    private Main main;
    private Wind windModel;
    String base;
}
