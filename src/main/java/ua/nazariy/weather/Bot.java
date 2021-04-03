package ua.nazariy.weather;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.nazariy.weather.commands.StartCommand;
import ua.nazariy.weather.commands.WeatherCommand;

public class Bot extends TelegramLongPollingCommandBot {
    public Bot(DefaultBotOptions botOptions){
        super(botOptions);
        register(new WeatherCommand("/weather", "send you current weather"));
        register(new StartCommand("/start", "start"));
    }

    @Override
    public String getBotUsername() {
        return "NazariusWeatherBOT";
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    @Override
    public String getBotToken() {
        return "1695531099:AAG0X1aGXAa0q_sbcx7QD7iSjWQ998vNDi0";
    }
}
