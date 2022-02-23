package org.helper.commands;

import org.helper.commands.launcher.ActionExecutor;
import org.helper.impl.IMessage;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartChatCommand extends IMessage {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public StartChatCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
        String message = "⚡️<b> Хэй, как твои дела? Шутка, мне это неинтересно. \n\n\uD83D\uDDD2 Ты можешь воспользоваться этими командами :</b> \n" +
                "├<code> /matches</code>  Покажет все матчи и даст предикт.\n" +
                "├<code> /support</code>  Полезная информация по использованию бота.\n" +
                "├<code> /donate</code>   Поддержка проекта.\n" +
                "└<code> /about</code>    О нашем проекте.\n"
                ;
        SendMessage sendMessage = generateMessage(action, message);
        ActionExecutor.send(sendMessage);
    }
}
