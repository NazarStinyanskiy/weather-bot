package ua.nazariy.weather.config.parsers.config;

import ua.nazariy.weather.config.Config;
import ua.nazariy.weather.config.parsers.Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ConfigParser implements Parser {
    private Config config;
    private Properties properties;

    public ConfigParser(){
        init();
    }

    public ConfigParser(String path) {
        init();
        setup(path);
    }

    protected void init() {
        config = new Config();
        properties = new Properties();
    }

    protected void setup(String path) {
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePath(String path){
        init();
        setup(path);
    }

    public Config parse() {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            config.putProperty((String) entry.getKey(), (String) entry.getValue());
        }
        return config;
    }
}
