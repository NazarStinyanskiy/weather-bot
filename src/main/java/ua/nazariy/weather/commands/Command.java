package ua.nazariy.weather.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.nazariy.weather.Settings;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.db.pojo.UserModel;
import ua.nazariy.weather.lang.Language;

import java.util.Map;

public interface Command {

    default boolean execute(AbsSender sender, SendMessage sendMessage) {
        try {
            sender.execute(sendMessage);
            return true;
        } catch (TelegramApiException e) {
            System.out.println("[WARN] The message: " + sendMessage.getText() + ", were not send");
        }

        return false;
    }

    default Language defineLanguage(User user){
        String userLanguageCode = UserConnection.select(user.getId()).getLanguage();
        if(userLanguageCode != null){
            return defineLanguage(userLanguageCode);
        } else {
            return defineLanguage(user.getLanguageCode());
        }
    }

    default Language defineLanguage(String countryCode){
        for (Map.Entry<String, Language> entry : Settings.getLanguages().entrySet()) {
            if(countryCode.equals(entry.getKey())) return entry.getValue();
        }

        return Settings.getLanguages().get("en");
    }

    default void writeUserIntoDB(long id){
        UserModel user = UserConnection.select(id);
        if(user == null){
            user = new UserModel();
            user.setUserId(id);
            user.setState(UserModel.State.START);
            UserConnection.write(user);
        }
    }

    default SendMessage getSendMessage(long chatId){
        return getSendMessage(chatId, "");
    }

    default SendMessage getSendMessage(long chatId, String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        sendMessage.setReplyMarkup(new ReplyKeyboardRemove());

        return sendMessage;
    }

    default boolean validateUserState(long userId, String expected){
        return UserConnection.select(userId).getState().equals(expected);
    }

    default boolean updateState(long userId, String state){
        return UserConnection.updateState(userId, state);
    }
}
