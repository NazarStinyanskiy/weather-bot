package ua.nazariy.weather;

import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.config.factory.FactoryStorage;
import ua.nazariy.weather.config.factory.OpenWeatherMapFactory;
import ua.nazariy.weather.config.parsers.ConfigParser;
import ua.nazariy.weather.lang.ENLang;
import ua.nazariy.weather.lang.Language;

import java.util.ArrayList;
import java.util.List;

public class Setting {
    private static final String CONFIG_PATH = "src/main/resources/config.properties";
    private static final String SECURE_CONFIG_PATH = "src/main/resources/secure-info.properties";

    private static Config config;
    private static Config secureConfig;

    private static List<Language> languages;

    public static void onStart(){
        init();

        parseConfig();
        langSetup();
        registerFactories();
    }

    private static void init(){
        languages = new ArrayList<>();
    }

    private static void parseConfig(){
        ConfigParser configParser = new ConfigParser(CONFIG_PATH);
        config = configParser.parse();

        configParser = new ConfigParser(SECURE_CONFIG_PATH);
        secureConfig = configParser.parse();
    }

    private static void langSetup(){
        languages.add(new ENLang());
    }

    private static void registerFactories(){
        FactoryStorage.registerFactory("weather", OpenWeatherMapFactory.class);
    }

    public static Config getConfig() {
        return config;
    }

    public static void setConfig(Config config) {
        Setting.config = config;
    }

    public static Config getSecureConfig() {
        return secureConfig;
    }

    public static void setSecureConfig(Config secureConfig) {
        Setting.secureConfig = secureConfig;
    }
}