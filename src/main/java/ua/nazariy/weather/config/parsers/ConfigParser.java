package ua.nazariy.weather.config.parsers;

import ua.nazariy.weather.config.Config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ConfigParser {
    private Config config;
    private Properties properties;

    public ConfigParser(String path) {
        init();
        setup(path);
    }

    public void init() {
        config = new Config();
        properties = new Properties();
    }

    public void setup(String path) {
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Config parse() {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            config.putProperty((String) entry.getKey(), (String) entry.getValue());
        }
        return config;
    }
}
