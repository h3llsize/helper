package org.helper.commands;

import org.helper.commands.launcher.ActionExecutor;
import org.helper.impl.IMessage;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class DonateChatCommand extends IMessage {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public DonateChatCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
        String message = "<b>Чтобы у нас были средства для существования, предлагаю вам поддержать нас </b> \n" +
                "\n" +
                "<b> \uD83D\uDCB3 Card : 5469 4900 1661 4477 </b>\n" +
                "<b> \uD83D\uDCB3 Qiwi : +79026370145 </b>\n\n";
        SendMessage sendMessage = generateMessage(action, message);
        ActionExecutor.send(sendMessage);
    }
}
