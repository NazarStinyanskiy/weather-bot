package ua.nazariy.weather.lang;

import java.util.HashMap;
import java.util.Map;

public class Language {

    public interface Flags{
        String EN = "\uD83C\uDDF1\uD83C\uDDF7";
        String RU = "\uD83C\uDDF7\uD83C\uDDFA";
        String UA = "\uD83C\uDDFA\uD83C\uDDE6";
    }

    private Map<String, String> speeches;

    public Language(){
        speeches = new HashMap<>();
    }

    public void setSpeech(String key, String value){
        speeches.put(key, value);
    }

    public String getSpeech(String key){
        if(speeches.containsKey(key)){
            return speeches.get(key);
        }

        if(speeches.containsKey("internal.error")){
            System.err.println("[WARN] No such speech by key: " + key);
            return speeches.get("internal.error");
        } else {
            System.err.println("[WARN] internal.error key do not set");
            return null;
        }

    }
}
