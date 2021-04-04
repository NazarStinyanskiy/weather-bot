package ua.nazariy.weather;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.nazariy.weather.commands.LanguageCommand;
import ua.nazariy.weather.commands.StartCommand;
import ua.nazariy.weather.commands.WeatherCommand;
import ua.nazariy.weather.config.Config;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_TOKEN;

    public Bot(DefaultBotOptions botOptions){
        super(botOptions);
        Config secureConfig = Setting.getSecureConfig();
        BOT_TOKEN = secureConfig.getProperty("bot.token");

        register(new WeatherCommand("/weather", "send you current weather"));
        register(new StartCommand("/start", "start"));
        register(new LanguageCommand("/lang", "switch language"));
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
        return BOT_TOKEN;
    }
}
