package ua.nazariy.weather.config.services;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ServiceStorage {
    private static final Map<String, AbstractWeatherService> factories = new HashMap<>();

    public static void registerService(String key, Class<? extends AbstractWeatherService> service) {
        try {
            factories.put(key, service.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static AbstractWeatherService getService(String key) {
        if (factories.containsKey(key)) {
            return factories.get(key);
        }
        return null;
    }
}