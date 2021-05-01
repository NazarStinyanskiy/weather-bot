package ua.nazariy.weather.db.connection;

import ua.nazariy.weather.Settings;
import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.db.pojo.UserModel;

import java.sql.*;

public class UserConnection {
    private static final Config secureConfig = Settings.getSecureConfig();

    public static UserModel select(long userId){
        Connection connection = null;
        Statement statement = null;
        UserModel user = new UserModel();

        try {
            Class.forName(secureConfig.getProperty("db.driver"));
            connection = getConnection();
            statement = getStatement(connection);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE user_id = " + userId);
            if(!resultSet.next()){
                return null;
            }

            do {
                user.setUserId(resultSet.getLong("user_id"));
                user.setLanguage(resultSet.getString("language"));
                user.setPhone(resultSet.getString("phone_number"));
                user.setWeatherService(resultSet.getString("weather_service"));
                user.setState(resultSet.getString("state"));
            } while (resultSet.next());

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }

        return user;
    }

    public static boolean write(UserModel userModel){
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(secureConfig.getProperty("db.driver"));
            connection = getConnection();
            statement = getStatement(connection);

            statement.executeUpdate("INSERT INTO users VALUES ("
                    + userModel.getUserId() + ", "
                    + userModel.getLanguage() + ", "
                    + userModel.getPhone() + ", "
                    + userModel.getWeatherService() + ", "
                    + "'" + userModel.getState() + "'" + ")"
            );

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, statement);
        }

        return true;
    }

    public static boolean updateLanguage(long userId, String language){
        return update(userId, language, "language");
    }

    public static boolean updateWeatherService(long userId, String weatherService){
        return update(userId, weatherService, "weather_service");
    }

    public static boolean updateState(long userId, String state){
        return update(userId, state, "state");
    }

    private static boolean update(long userId, String arg, String column){
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(secureConfig.getProperty("db.driver"));
            connection = getConnection();
            statement = getStatement(connection);

            statement.executeUpdate("UPDATE users SET " + column  + "= '" + arg + "' WHERE user_id = " + userId);

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, statement);
        }

        return true;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(secureConfig.getProperty("db.url"), secureConfig.getProperty("db.user"), secureConfig.getProperty("db.password"));
    }

    private static Statement getStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    private static void closeConnection(Connection connection, Statement statement){
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
}
