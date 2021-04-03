package ua.nazariy.weather.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.nazariy.weather.lang.ENLang;
import ua.nazariy.weather.lang.Language;

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
        language = defineLanguage(message.getFrom());
        super.processMessage(absSender, message, arguments);
    }

    protected Language defineLanguage(User user){
        if(user.getLanguageCode().equals("en")){
            return new ENLang();
        } else {
            return new ENLang();
        }
    }
}
