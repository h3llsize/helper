package org.helper.main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.helper.commands.launcher.ActionLauncher;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return Config.botUserName;
    }

    @Override
    public String getBotToken() {
        return Config.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage());
        try {
            ActionLauncher.execute(update);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
