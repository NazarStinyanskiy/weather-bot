package ua.nazariy.weather;

import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.config.parsers.ConfigParser;
import ua.nazariy.weather.config.parsers.LangParser;
import ua.nazariy.weather.config.services.OpenWeatherMapService;
import ua.nazariy.weather.config.services.ServiceStorage;
import ua.nazariy.weather.lang.Language;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    private static final String CONFIG_PATH = "src/main/resources/config.properties";
    private static final String SECURE_CONFIG_PATH = "src/main/resources/secure-info.properties";

    private static Config config;
    private static Config secureConfig;

    private static Map<String, Language> languages;

    public static void onStart() {
        init();

        parseConfig();
        langSetup();
        registerService();
    }

    private static void init() {
        languages = new HashMap<>();
    }

    private static void parseConfig() {
        ConfigParser configParser = new ConfigParser(CONFIG_PATH);
        config = configParser.parse();

        configParser.changePath(SECURE_CONFIG_PATH);
        secureConfig = configParser.parse();
    }

    private static void langSetup() {
        File root = new File("src/main/resources/langs");
        if (!root.isDirectory()) {
            System.err.println("[WARN] " + root + " is not a directory. Can not find languages!");
            return;
        }

        String[] paths = root.list();
        if(paths == null){
            System.err.println("[WARN] Can not find languages in " + root);
            return;
        }

        LangParser parser = new LangParser();
        for (String path : paths) {
            String lang = path.substring(0, path.indexOf('.'));
            languages.put(lang, parser.parse(root + "/" +path));
        }
    }

    private static void registerService() {
        ServiceStorage.registerService("open_weather_map", OpenWeatherMapService.class);
    }

    public static Config getConfig() {
        return config;
    }

    public static Config getSecureConfig() {
        return secureConfig;
    }

    public static Map<String, Language> getLanguages() {
        return languages;
    }
}