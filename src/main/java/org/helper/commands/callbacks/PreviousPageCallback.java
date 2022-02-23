package org.helper.commands.callbacks;

import org.helper.impl.ICallback;
import org.helper.impl.IUser;
import org.helper.main.Bot;
import org.helper.parser.MatchesKeyboard;
import org.helper.parser.ParserStorage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PreviousPageCallback extends ICallback {
    public PreviousPageCallback(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, IUser user) {
        user.currentMatchPage -= 1;

        if(user.currentMatchPage < 0)
        {
            user.currentMatchPage = ParserStorage.inlineKeyboardMarkups.size() - 1;
        }

        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setText("<b>Матчи : Страница " + (user.currentMatchPage + 1) + "</b>");
        sendMessage.enableHtml(true);
        sendMessage.setChatId(action.getCallbackQuery().getMessage().getChatId().toString());
        sendMessage.setMessageId(action.getCallbackQuery().getMessage().getMessageId());

        try {
            sendMessage.setReplyMarkup(new MatchesKeyboard(user.currentMatchPage, user).createKeyboard());
        }
        catch (Exception e)
        {
            sendMessage.setText("<b>Матчи обновляются!</b>");
            e.printStackTrace();
        }

        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
