package ua.nazariy.weather.commands.slash;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.AbstractCommand;
import ua.nazariy.weather.db.pojo.UserPOJO;

import java.util.ArrayList;
import java.util.List;

public class ServiceCommand extends AbstractCommand {

    public ServiceCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = getSendMessage(chat.getId(), language.getSpeech("service.command"));
        message.setReplyMarkup(setupKeyboardMarkup());
        if(!updateState(user.getId(), UserPOJO.State.SERVICE)){
            message.setText(language.getSpeech("internal.error"));
        }
        execute(absSender, message);
    }

    private ReplyKeyboardMarkup setupKeyboardMarkup(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Open Weather Map");
        row.add("Weatherstack");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
}
