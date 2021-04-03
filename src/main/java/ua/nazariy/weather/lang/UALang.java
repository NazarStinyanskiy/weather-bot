package ua.nazariy.weather.lang;

public class UALang extends Language {
    public UALang(){
        START_GREETINGS =
                """
                Вітаємо в нашому (а тепер і вашому) боті!
                Ми готові виконувати ваші команди! Для початку, напишіть: /weather

                Якщо потрібна допомога використай /help
                """;

        WEATHER_COMMAND =
                """
                Виберіть сервіс, яким ви хочете скористуватись: \n/open_weather_map
                """;

        HELP_COMMAND =
                """
                /weather - щоб вибрати необхібний погодний сервіс
                """;
    }
}
