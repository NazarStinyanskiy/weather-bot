package ua.nazariy.weather.lang;

import java.util.Objects;

public class Language {
    public String LANGUAGE_FULL;

    public String START_GREETINGS;
    public String WEATHER_COMMAND;
    public String HELP_COMMAND;
    public String NO_SUCH_LANGUAGE;
    public String LANGUAGE_WERE_SUCCESSFULLY_CHANGED;

    public String INTERNAL_ERROR;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(START_GREETINGS, language.START_GREETINGS) && Objects.equals(WEATHER_COMMAND, language.WEATHER_COMMAND) && Objects.equals(HELP_COMMAND, language.HELP_COMMAND);
    }

    @Override
    public int hashCode() {
        return Objects.hash(START_GREETINGS, WEATHER_COMMAND, HELP_COMMAND);
    }
}
