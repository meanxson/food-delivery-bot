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
import java.util.Objects;

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
                        username,
                        "categories"
                );
            }
            for (int i = 0; i < foodDelivery.getCategories().size(); i++) {
                if (foodDelivery.getCategories().get(i).equalsIgnoreCase(text)) {
                    sendCustomKeyboard(
                            update.getMessage().getChatId().toString(),
                            "subcategories",
                            i
                    );
                    break;
                }
            }
            for (int i = 0; i < foodDelivery.getCategories().size(); i++) {
                for (String item : foodDelivery.getSubcategories().get(i)) {
                    if (item.equals(text)) {
                        // search user in users
                        for (User user : users) {
                            if (Objects.equals(user.getID(), ID)) {
                                user.addToBasket(item);
                                break;
                            }
                        }
                        sendCustomKeyboard(
                                update.getMessage().getChatId().toString(),
                                username,
                                "categories"
                        );
                        break;
                    }
                }
            }
        }
    }

    public void sendCustomKeyboard(String chatId, String username, String resources) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(Information.getStartInform(username) + "\nChoose food:");

        List<String> list;

        if ("categories".equals(resources)) {
            list = foodDelivery.getCategories();
        } else {
            list = new ArrayList<>();
        }

        sendCustomKeyboard(message,  list);
    }

    public void sendCustomKeyboard(String chatId, String resources, int indexResources) {
        SendMessage message = new SendMessage();
        List<String> list;

        message.setChatId(chatId);
        message.setText("Please, choose " + foodDelivery.getCategories().get(indexResources).toLowerCase());

        if ("subcategories".equals(resources)) {
            list = foodDelivery.getSubcategories().get(indexResources);
        } else {
            list = new ArrayList<>();
        }
        sendCustomKeyboard(message, list);
    }

    private void sendCustomKeyboard(SendMessage message, List<String> list) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

            if (list.size() > 3) {
                for (int i = 0, count = 0; i < list.size(); i++) {
                    if (count++ < 3) {
                        row.add(list.get(i));
                    } else {
                        keyboard.add(row);
                        row = new KeyboardRow();
                        row.add(list.get(i));
                        if (i % 3 == 0) {
                            keyboard.add(row);
                        }
                        count = 0;
                    }
                }
            } else {
                for (String s : list) {
                    row.add(s);
                }
                keyboard.add(row);
            }
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
