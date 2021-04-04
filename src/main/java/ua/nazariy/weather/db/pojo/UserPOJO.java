package ua.nazariy.weather.db.pojo;

public class UserPOJO {
    private long userId;
    private String language;
    private String phone;

    public UserPOJO(){

    }

    public UserPOJO(long userId, String language, String phone) {
        this.userId = userId;
        this.language = language;
        this.phone = phone;
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
}
