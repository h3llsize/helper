package org.helper.commands;

import org.helper.commands.launcher.ActionExecutor;
import org.helper.impl.IMessage;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownChatCommand extends IMessage {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public UnknownChatCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
        String message = "<b> Ошибка! Такой команды не существует! \uD83E\uDD28 </b>";
        SendMessage sendMessage = generateMessage(action, message);
        ActionExecutor.send(sendMessage);
    }

}
