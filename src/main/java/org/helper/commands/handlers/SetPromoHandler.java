package org.helper.commands.handlers;

import org.helper.commands.launcher.ActionExecutor;
import org.helper.main.Bot;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SetPromoHandler {
    public SetPromoHandler(User user, String promo) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.chatId));
        sendMessage.setText("⚡️ <b>Вы успешно указали промокод!</b>");
        sendMessage.enableHtml(true);

        try {
            user.setReferal(promo);
        } catch (Exception e) {
            sendMessage.setText("\uD83E\uDD76 <b>Промокода не существует</b>");
        }
        ActionExecutor.send(sendMessage);
    }
}
