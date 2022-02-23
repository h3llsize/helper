package org.helper.commands.launcher;

import org.helper.main.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ExecuteMessage extends Thread{
    private final SendMessage sendMessage;

    public ExecuteMessage(SendMessage sendMessage)
    {
        this.sendMessage = sendMessage;
        start();
    }
    @Override
    public void run() {
        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
