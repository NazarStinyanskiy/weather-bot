package ua.nazariy.weather.db.connection;

import ua.nazariy.weather.Settings;
import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.db.pojo.UserPOJO;

import java.sql.*;

public class UserConnection {
    private static final Config secureConfig = Settings.getSecureConfig();

    public static UserPOJO select(long userId){
        Connection connection = null;
        Statement statement = null;
        UserPOJO user = new UserPOJO();

        try {
            Class.forName(secureConfig.getProperty("db.driver"));
            connection = DriverManager.getConnection(secureConfig.getProperty("db.url"), secureConfig.getProperty("db.user"), secureConfig.getProperty("db.password"));
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE user_id = " + userId);

            if(!resultSet.next()){
                return null;
            }

            do {
                user.setUserId(resultSet.getLong("user_id"));
                user.setLanguage(resultSet.getString("language"));
                user.setPhone(resultSet.getString("phone_number"));
                user.setWeatherService(resultSet.getString("weather_service"));
            } while (resultSet.next());

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public static boolean write(UserPOJO userPOJO){
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(secureConfig.getProperty("db.driver"));
            connection = DriverManager.getConnection(secureConfig.getProperty("db.url"), secureConfig.getProperty("db.user"), secureConfig.getProperty("db.password"));
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO users VALUES ("
                    + userPOJO.getUserId() + ", "
                    + userPOJO.getLanguage() + ", "
                    + userPOJO.getPhone() + ", "
                    + userPOJO.getWeatherService() + ")"
            );

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public static boolean updateLanguage(long userId, String language){
        return update(userId, language, "language");
    }

    public static boolean updateWeatherService(long userId, String weatherService){
        return update(userId, weatherService, "weather_service");
    }

    private static boolean update(long userId, String arg, String column){
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(secureConfig.getProperty("db.driver"));
            connection = DriverManager.getConnection(secureConfig.getProperty("db.url"), secureConfig.getProperty("db.user"), secureConfig.getProperty("db.password"));
            statement = connection.createStatement();

            statement.executeUpdate("UPDATE users SET " + column  + "= '" + arg + "' WHERE user_id = " + userId);

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
