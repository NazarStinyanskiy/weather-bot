package ua.nazariy.weather.models;

public interface Model {

    /**
     overriders should return null if they don't have temperature
     **/
    Double getTemperature();

    /**
     overriders should return null if they don't have feels like temperature
     **/
    Double getFeelsLikeTemperature();

    /**
     overriders should return null if they don't have description
     **/
    String getDescription();
}
