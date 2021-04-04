package ua.nazariy.weather;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.nazariy.weather.commands.*;
import ua.nazariy.weather.config.Config;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_TOKEN;

    public Bot(DefaultBotOptions botOptions){
        super(botOptions);
        Config secureConfig = Settings.getSecureConfig();
        BOT_TOKEN = secureConfig.getProperty("bot.token");

        register(new StartCommand("/start", "start"));
        register(new HelpCommand("/help", "print a list of available commands"));
        register(new WeatherCommand("/weather", "sending you a current weather"));
        register(new LanguageCommand("/lang", "switching language"));
        register(new LangsCommand("/langs", "shows all available languages"));
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
