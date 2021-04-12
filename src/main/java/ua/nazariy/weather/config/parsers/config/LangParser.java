package ua.nazariy.weather.config.parsers.config;

import ua.nazariy.weather.Settings;
import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.config.parsers.Parser;
import ua.nazariy.weather.lang.Language;

import java.util.Map;

public class LangParser implements Parser {
    ConfigParser parser;

    public LangParser(){
        parser = new ConfigParser();
    }

    public Language parse(String path){
        Language language = new Language();

        parser.changePath(path);
        Config langConfig = parser.parse();

        parser.changePath(Settings.getConfig().getProperty("lang.dir") + "/" + Settings.getConfig().getProperty("default.lang"));
        Config defaultLangConfig = parser.parse();

        for (Map.Entry<String, String> defaultSpeech : defaultLangConfig.getConfig().entrySet()) {
            if(langConfig.getProperty(defaultSpeech.getKey()) != null){
                language.setSpeech(defaultSpeech.getKey(), langConfig.getProperty(defaultSpeech.getKey()));
            } else {
                language.setSpeech(defaultSpeech.getKey(), defaultSpeech.getValue());
            }
        }
        for (Map.Entry<String, String> entry : langConfig.getConfig().entrySet()) {
            language.setSpeech(entry.getKey(), entry.getValue());
        }
        return language;
    }
}
