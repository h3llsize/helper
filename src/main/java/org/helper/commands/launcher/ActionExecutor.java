package org.helper.commands.launcher;

import org.helper.main.Bot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ActionExecutor {
    public static void send(SendMessage sendMessage)
    {
        new ExecuteMessage(sendMessage);
    }
}
