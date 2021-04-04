package ua.nazariy.weather.lang;

public class UALang extends Language {
    public UALang(){
        LANGUAGE_FULL =
                """
                Українська
                """;

        START_GREETINGS =
                """
                Вітаємо в нашому (а тепер і вашому) боті!
                Ми готові виконувати ваші команди! Для початку, напишіть: /weather

                Якщо потрібна допомога використай /help
                """;

        WEATHER_COMMAND =
                """
                Виберіть сервіс, яким ви хочете скористуватись:
                /open_weather_map
                """;

        HELP_COMMAND =
                """
                /weather - щоб вибрати необхібний погодний сервіс
                /lang [language] - щоб вибрати необхібну мову. Наприклад /lang en - щоб вибрати англійську
                """;

        NO_SUCH_LANGUAGE =
                """
                Покищо ми не підтримуємо цю мову. Спробуйте /langs щоб побачити всі доступні мови
                """;

        LANGUAGE_WERE_SUCCESSFULLY_CHANGED =
                """
                Мову успішно змінено!
                """;

        INTERNAL_ERROR =
                """
                Нажаль відбулась внутрішня помилика. Ми вже працюємо над цим!
                """;
    }
}
