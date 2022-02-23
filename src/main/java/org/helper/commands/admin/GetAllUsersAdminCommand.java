package org.helper.commands.admin;

import org.helper.database.DBLoader;
import org.helper.impl.IAdmin;
import org.helper.impl.IMessage;
import org.helper.main.Bot;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class GetAllUsersAdminCommand extends IAdmin {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public GetAllUsersAdminCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(action.getMessage().getChatId().toString());
        String[] list = DBLoader.getAllUsers().split("&");

        StringBuilder s = new StringBuilder();

        System.out.println(list.length);

        for(int i = 0; i < list.length; i++)
        {
            s.append(list[i]);
            if(i%15==0) {
                sendMessage.setText(s.toString());
                s = new StringBuilder();
                try {
                    new Bot().execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        sendMessage.setText("Count of USERS : " + list.length);
        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
