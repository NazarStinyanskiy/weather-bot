package ua.nazariy.weather.models.open_weather_map;

import ua.nazariy.weather.models.Model;

public class OpenWeatherMapModel implements Model {
    private Coord coordModel;
    private Weather[] weather;
    private Clouds cloudsModel;
    private Main main;
    private Wind windModel;
    private String base;
    private int timezone;
    private long id;
    private String name;
    private int cod;

    public Coord getCoordModel() {
        return coordModel;
    }

    public void setCoordModel(Coord coordModel) {
        this.coordModel = coordModel;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Clouds getCloudsModel() {
        return cloudsModel;
    }

    public void setCloudsModel(Clouds cloudsModel) {
        this.cloudsModel = cloudsModel;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWindModel() {
        return windModel;
    }

    public void setWindModel(Wind windModel) {
        this.windModel = windModel;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
