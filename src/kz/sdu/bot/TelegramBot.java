package kz.sdu.bot;

import kz.sdu.information.Information;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import kz.sdu.account.User;
import java.util.ArrayList;
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
        final String username = update.getMessage().getChat().getUserName();
        final Long ID = update.getMessage().getChat().getId();
        final String text = update.getMessage().getText();
        authUsers(username, ID);
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            if (text.equals("/start")) {
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText(Information.getStartInform(username));
                try {
                    execute(message);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void authUsers(String username, Long ID) {
        if (!users.isEmpty())
            for (User user : users)
                if (user.getID().equals(ID)) {
                    if (!user.getUsername().equals(username))
                        user.setUsername(username);
                    return;
                }
        users.add(new User(username, ID));
    }
}
