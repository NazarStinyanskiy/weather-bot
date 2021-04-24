package ua.nazariy.weather.db.pojo;

public class UserPOJO {
    private long userId;
    private String language;
    private String phone;
    private String weatherService;
    private String state;

    public interface State {
        String START = "start";
        String SERVICE = "service";
        String WEATHER = "weather";
    }

    public UserPOJO(){

    }

    public UserPOJO(long userId, String language, String phone, String state) {
        this.userId = userId;
        this.language = language;
        this.phone = phone;
        this.state = state;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeatherService() {
        return weatherService;
    }

    public void setWeatherService(String weatherService) {
        this.weatherService = weatherService;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
