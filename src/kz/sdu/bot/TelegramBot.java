package kz.sdu.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
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

    }
}
