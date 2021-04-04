package ua.nazariy.weather.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.nazariy.weather.Settings;
import ua.nazariy.weather.db.connection.UserConnection;
import ua.nazariy.weather.db.pojo.UserPOJO;
import ua.nazariy.weather.lang.Language;

import java.util.Map;

public abstract class AbstractCommand extends BotCommand {
    protected Language language;

    public AbstractCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    protected boolean execute(AbsSender sender, SendMessage sendMessage) {
        try {
            sender.execute(sendMessage);
            return true;
        } catch (TelegramApiException e) {
            System.out.println("[WARN] The message: " + sendMessage.getText() + ", were not send");
        }

        return false;
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        UserPOJO user = UserConnection.select(message.getFrom().getId());
        if(user == null){
            user = new UserPOJO();
            user.setUserId(message.getFrom().getId());
            UserConnection.write(user);
        }

        String userLanguageCode = UserConnection.select(message.getFrom().getId()).getLanguage();
        language = userLanguageCode == null ? defineLanguage(message.getFrom().getLanguageCode()) : defineLanguage(userLanguageCode);
        if(language == null) language = Settings.getLanguages().get("en");

        super.processMessage(absSender, message, arguments);
    }

    protected Language defineLanguage(String countryCode){
        for (Map.Entry<String, Language> entry : Settings.getLanguages().entrySet()) {
            if(countryCode.equals(entry.getKey())) return entry.getValue();
        }

        return null;
    }

    protected String concatenateArgs(String[] arguments){
        StringBuilder param = new StringBuilder();
        for (String s : arguments) {
            param.append(s);
        }
        return param.toString();
    }
}
