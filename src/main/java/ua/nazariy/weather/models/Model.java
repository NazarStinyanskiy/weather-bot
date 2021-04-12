package ua.nazariy.weather.models;

public interface Model {

    /**
     overriders should return null if they don't support place name
     **/
    String getPlaceName();

    /**
     overriders should return null if they don't support temperature
     **/
    Double getTemperature();

    /**
     overriders should return null if they don't support feels like temperature
     **/
    Double getFeelsLikeTemperature();

    /**
     overriders should return null if they don't support description
     **/
    String getDescription();
}