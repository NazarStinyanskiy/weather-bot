package ua.nazariy.weather.lang;

public class ENLang extends Language {
    public ENLang(){
        LANGUAGE_FULL =
                """
                English
                """;

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
                /lang [language] - to choose language. Example /lang en - To use english
                """;

         NO_SUCH_LANGUAGE =
                 """
                 We do not support this language yet. Type /langs to see all supported languages
                 """;

         LANGUAGE_WERE_SUCCESSFULLY_CHANGED =
                 """
                 Language were successfully changed!
                 """;

         INTERNAL_ERROR =
                 """
                 Unfortunately there was internal error. We already working on it!
                 """;
    }
}
