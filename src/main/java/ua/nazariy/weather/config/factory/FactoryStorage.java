package ua.nazariy.weather.config.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FactoryStorage {
    private static final Map<String, AbstractFactory> factories = new HashMap<>();

    public static void registerFactory(String key, Class<? extends AbstractFactory> factory) {
        try {
            factories.put(key, factory.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static AbstractFactory getFactory(String key) {
        if (factories.containsKey(key)) {
            return factories.get(key);
        }
        return null;
    }
}