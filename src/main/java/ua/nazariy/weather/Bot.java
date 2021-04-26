package ua.nazariy.weather;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.nazariy.weather.commands.no_slash.AbstractNoSlashCommand;
import ua.nazariy.weather.commands.no_slash.NoSlashCommandStorage;
import ua.nazariy.weather.commands.no_slash.OpenWeatherMapCommand;
import ua.nazariy.weather.commands.no_slash.WeatherStackCommand;
import ua.nazariy.weather.commands.no_slash.lang.EnCommand;
import ua.nazariy.weather.commands.no_slash.lang.RuCommand;
import ua.nazariy.weather.commands.no_slash.lang.UaCommand;
import ua.nazariy.weather.commands.slash.HelpCommand;
import ua.nazariy.weather.commands.slash.LanguageCommand;
import ua.nazariy.weather.commands.slash.ServiceCommand;
import ua.nazariy.weather.commands.slash.StartCommand;
import ua.nazariy.weather.commands.slash.LangsCommand;
import ua.nazariy.weather.commands.slash.WeatherCommand;

import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.db.pojo.UserPOJO;
import ua.nazariy.weather.lang.Language;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_TOKEN;

    public Bot(DefaultBotOptions botOptions){
        super(botOptions);
        Config secureConfig = Settings.getSecureConfig();
        BOT_TOKEN = secureConfig.getProperty("bot.token");

        register(new StartCommand("/start", "start"));
        register(new HelpCommand("/help", "print a list of available commands"));
        register(new ServiceCommand("/service", "switching weather service"));
        register(new LanguageCommand("/lang", "switching language"));
        register(new LangsCommand("/langs", "shows all available languages"));
        register(new WeatherCommand("/weather", "sending current weather"));

        NoSlashCommandStorage.register(new OpenWeatherMapCommand("Open Weather Map", UserPOJO.State.SERVICE));
        NoSlashCommandStorage.register(new WeatherStackCommand("Weatherstack", UserPOJO.State.SERVICE));
        NoSlashCommandStorage.register(new EnCommand(Language.Flags.EN, UserPOJO.State.LANG));
        NoSlashCommandStorage.register(new RuCommand(Language.Flags.RU, UserPOJO.State.LANG));
        NoSlashCommandStorage.register(new UaCommand(Language.Flags.UA, UserPOJO.State.LANG));
    }

    @Override
    public String getBotUsername() {
        return "NazariusWeatherBOT";
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        AbstractNoSlashCommand command = NoSlashCommandStorage.getCommand(update.getMessage().getText());
        if(command == null){
            return;
        }

        command.processMessage(update, this);
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
