package ua.nazariy.weather.config;

import java.util.HashMap;
import java.util.Map;

public class Config {
    private final Map<String, String> config = new HashMap<>();

    public void putProperty(String key, String value){
        config.put(key, value);
    }

    public String getProperty(String key){
        return config.get(key);
    }

    public Map<String, String> getConfig(){
        return config;
    }
}
