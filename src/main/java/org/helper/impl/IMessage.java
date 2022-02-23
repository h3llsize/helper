package org.helper.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.helper.commands.launcher.ActionStorage;
import org.helper.user.User;

public abstract class IMessage {
    public final String name;
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public IMessage(String name) {
        this.name = name;
        initialize();
    }

    /**
     * Этот метод отвечает за выполнения действие в самой команде
     *
     * new Bot().execute(SendMessage и т.п)
     * @param action пользователя
     */
    public abstract void exec(Update action, User user);

    public SendMessage generateMessage(Update action, String message)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(action.getMessage().getChatId().toString());
        sendMessage.enableHtml(true);

        return sendMessage;
    }


    /**
     * Для удобства мы будем сразу же, после создания класса инициализировать его и добавлять в Map
     * Таким образом нам придётся писать меньше кода.
     */

    public void initialize()
    {
        ActionStorage.addMessageAction(this, this.name);
    }

    @Override
    public String toString() {
        return "Action : " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IMessage){
            if (name.equals(((IMessage) obj).name)){
                return true;
            }
        }
        return false;
    }
}
