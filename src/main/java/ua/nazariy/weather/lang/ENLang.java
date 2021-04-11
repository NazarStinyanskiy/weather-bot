package ua.nazariy.weather.lang;

public class ENLang extends Language {
    public ENLang(){
        LANGUAGE_FULL =
                """
                English""";

        LANGUAGE_SHORT =
                """
                EN""";

        START_GREETINGS =
                """
                Welcome, to our (and now your) weather bot!
                We are ready to serve you. Type /weather

                If you need help use /help""";

        SERVICE_COMMAND =
                """
                Choose service witch you want to use:
                /open_weather_map""";

        HELP_COMMAND =
                """
                /service - to choose weather service
                /lang [language] - to choose language. Example /lang en - To use english
                /langs - to see all available languages
                /weather [city] - to see weather at the [city]""";

         NO_SUCH_LANGUAGE =
                 """
                 We do not support this language yet. Type /langs to see all supported languages""";

         LANGUAGE_WERE_SUCCESSFULLY_CHANGED =
                 """
                 Language were successfully changed!""";

         INTERNAL_ERROR =
                 """
                 Unfortunately there was internal error. We already working on it!""";

        NO_SERVICE_CHOSEN =
                """
                You did not choose weather service. Type /service to view all available""";

        NO_SUCH_SERVICE =
                """
                We do not support this service. Type /service to view all available""";

        SERVICE_SUCCESSFULLY_CHANGED =
                """
                Service was successfully changed!""";

        NO_CITY_ENTERED =
                """
                You didn't enter the city!""";

        CITY_NOT_FOUND =
                """
                The city not found!""";

        TEMPERATURE =
                """
                Temperature:\040""";

        FEELS_LIKE =
                """
                Feels like:\040""";
    }
}
