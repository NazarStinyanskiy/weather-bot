package ua.nazariy.weather.db.connection;

import ua.nazariy.weather.Setting;
import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.db.pojo.UserPOJO;

import java.sql.*;

public class UserConnection {
    private static final Config secureConfig = Setting.getSecureConfig();

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

            statement.executeUpdate("INSERT INTO users VALUES (" + userPOJO.getUserId() + ", " + userPOJO.getLanguage() + ", " + userPOJO.getPhone() + ")");

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
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(secureConfig.getProperty("db.driver"));
            connection = DriverManager.getConnection(secureConfig.getProperty("db.url"), secureConfig.getProperty("db.user"), secureConfig.getProperty("db.password"));
            statement = connection.createStatement();

            statement.executeUpdate("UPDATE users SET language = '" + language + "' WHERE user_id = " + userId);

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
