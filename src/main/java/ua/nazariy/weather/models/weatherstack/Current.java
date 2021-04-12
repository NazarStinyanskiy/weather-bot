package ua.nazariy.weather.models.weatherstack;

public class Current {
    private int temperature;
    private String[] weather_descriptions;
    private int feelslike;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String[] getWeather_descriptions() {
        return weather_descriptions;
    }

    public void setWeather_descriptions(String[] weather_descriptions) {
        this.weather_descriptions = weather_descriptions;
    }

    public int getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(int feelslike) {
        this.feelslike = feelslike;
    }
}
