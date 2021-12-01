package kz.sdu.bot;

import kz.sdu.delivery.FoodDelivery;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import kz.sdu.information.Information;
import kz.sdu.account.User;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    List<User> users = new ArrayList<>();
    FoodDelivery foodDelivery = new FoodDelivery();

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (text.equals("/start")) {
                sendCustomKeyboard(
                        update.getMessage().getChatId().toString(),
                        username
                );
            }

        }
    }

    public void sendCustomKeyboard(String chatId, String username) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(Information.getStartInform(username) + "\nChoose food:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Burger");
        row.add("Beverages");
        row.add("Fries");
        keyboard.add(row);

        row = new KeyboardRow();
        row.add("Calculate final Price");
        row.add("Exit");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
