package ua.nazariy.weather.commands.slash;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.nazariy.weather.commands.AbstractCommand;
import ua.nazariy.weather.db.pojo.UserPOJO;
import ua.nazariy.weather.lang.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguageCommand extends AbstractCommand {
    public LanguageCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = getSendMessage(chat.getId(), language.getSpeech("language.command"));
        message.setReplyMarkup(setupKeyboardMarkup());
        updateState(user.getId(), UserPOJO.State.LANG);
        execute(absSender, message);
    }

    private ReplyKeyboardMarkup setupKeyboardMarkup(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(Language.Flags.EN);
        row.add(Language.Flags.RU);
        row.add(Language.Flags.UA);
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
}
