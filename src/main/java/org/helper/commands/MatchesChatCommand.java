package org.helper.commands;

import org.helper.impl.IMessage;
import org.helper.main.Bot;
import org.helper.main.Config;
import org.helper.parser.MatchesKeyboard;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.PromoteChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.UnbanChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MatchesChatCommand extends IMessage {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public MatchesChatCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
        if(!isJoiningPeople(user)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(action.getMessage().getChatId().toString());
            sendMessage.setText("<b>Для того чтобы использовать бота, вступите в наш Телеграм</b>");

            sendMessage.enableHtml(true);
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText("Ссылка");
            btn.setUrl("https://t.me/helperNEWS");

            InlineKeyboardButton btn2 = new InlineKeyboardButton();
            btn2.setText("Я подписался ✅");
            btn2.setCallbackData("/matches");

            List<InlineKeyboardButton> btns = new ArrayList<>();
            List<InlineKeyboardButton> btns2 = new ArrayList<>();
            List<List<InlineKeyboardButton>> row = new ArrayList<>();

            btns.add(btn);
            btns2.add(btn2);
            row.add(btns);
            row.add(btns2);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(row);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

            try {
                new Bot().execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }
        user.currentMatchPage = 0;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("<b>Матчи : Страница " + (user.currentMatchPage + 1) + "</b>");
        sendMessage.enableHtml(true);
        sendMessage.setChatId(action.getMessage().getChatId().toString());
        try {
            sendMessage.setReplyMarkup(new MatchesKeyboard(user.currentMatchPage, user).createKeyboard());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            sendMessage.setText("<b>\uD83C\uDF93 Матчи обновляются! Обычно это занимает не более часа. Всё зависит от их количества.</b>");
        }

        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean isJoiningPeople(User user)
    {
        GetChatMember chatMember = new GetChatMember();
        chatMember.setUserId(user.chatId);
        chatMember.setChatId(Config.channelId);
        try {
            String userStatus = new Bot().execute(chatMember).getStatus();

            return !userStatus.equals("kicked") && !userStatus.equals("left") && !userStatus.equals("restricted") && !userStatus.equals("banned");

        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }
}
