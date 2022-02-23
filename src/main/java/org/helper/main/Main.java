package org.helper.main;

import org.helper.weka.WekaStorage;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.helper.database.Connection;
import org.helper.timers.OneHourTimer;
import org.helper.user.UserLoader;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        System.setProperty("java.net.useSystemProxies", "true");

        Config.Initialize();
        Connection.Initialize();
        UserLoader.Initialize();
        WekaStorage.Initialize();

        Bot bot = new Bot();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        System.out.println("BOT STARTS WORK");

        OneHourTimer hour = new OneHourTimer();
        hour.start();
    }
}
