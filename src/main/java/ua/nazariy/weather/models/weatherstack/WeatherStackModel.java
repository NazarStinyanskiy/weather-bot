package ua.nazariy.weather.models.weatherstack;

import ua.nazariy.weather.models.Model;

import java.util.Arrays;

public class WeatherStackModel implements Model {
    private Request request;
    private Location location;
    private Current current;
    private Error error;

    @Override
    public String getPlaceName() {
        return location.getName() + " - " + location.getCountry();
    }

    @Override
    public Double getTemperature() {
        return (double) current.getTemperature();
    }

    @Override
    public Double getFeelsLikeTemperature() {
        return (double) current.getFeelslike();
    }

    @Override
    public String getDescription() {
        StringBuilder string = new StringBuilder();
        Arrays.stream(current.getWeather_descriptions()).forEach(string::append);
        return string.toString();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
