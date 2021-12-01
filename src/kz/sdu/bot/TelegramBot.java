package kz.sdu.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import kz.sdu.account.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    List<User> users = new ArrayList<>();

    @Override
    public String getBotUsername() {
        return "fooood_delivery_bot";
    }

    @Override
    public String getBotToken() {
        return "2130702439:AAFDouGPGU90JyB--82859SL7rU3DeprNQo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String username = update.getMessage().getChat().getUserName();
        final Long ID = update.getMessage().getChat().getId();
        authUsers(username, ID);
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

        }
    }

    public void authUsers(String username, Long ID) {
        if (!users.isEmpty())
            for (User user : users)
                if (user.getID().equals(ID)) return;
        users.add(new User(username, ID));
    }
}
