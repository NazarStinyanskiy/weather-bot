package ua.nazariy.weather.config.parsers;

import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.lang.Language;

import java.util.Map;

public class LangParser implements Parser{
    ConfigParser parser;

    public LangParser(){
        parser = new ConfigParser();
    }

    public Language parse(String path){
        Language language = new Language();
        parser.changePath(path);
        Config langConfig = parser.parse();
        for (Map.Entry<String, String> entry : langConfig.getConfig().entrySet()) {
            language.setSpeech(entry.getKey(), entry.getValue());
        }
        return language;
    }
}
