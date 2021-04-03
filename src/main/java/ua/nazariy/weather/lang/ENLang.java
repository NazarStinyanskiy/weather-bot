package ua.nazariy.weather.lang;

public class ENLang extends Language {
    public ENLang(){
        START_GREETINGS =
                """
                Welcome, to our (and now your) weather bot!
                We are ready to serve you. Type /weather

                If you need help use /help
                """;

        WEATHER_COMMAND =
                """
                Choose service witch you want to use: 
                /open_weather_map
                """;

        HELP_COMMAND =
                """
                /weather - to choose weather service
                """;
    }
}
