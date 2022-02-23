package org.helper.commands;

import org.helper.commands.launcher.ActionExecutor;
import org.helper.impl.IMessage;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AboutUsChatCommand extends IMessage {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public AboutUsChatCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
        String message = "\uD83C\uDF93<b> Наш проект - нейросеть, предсказывающая матчи по кс. </b>\n" +
                "Пока что мы находимся в разработке, все функции бота предоставлены бесплатно. Всё что от вас требуется - " +
                "подписаться на наш <b>TELEGRAM</b>\n\n" +
                "\uD83D\uDC64<b> Характеристики бота :</b> \n" +
                "1. Точность предсказания : <b>около 80%</b>️\n" +
                "2. Какие матчи вы предсказываете : <b>BO1, BO3, BO5</b>\n" +
                "3. Что вы собираете для анализа : <b>Почти всё, что в открытом доступе</b>\n" +
                "4. Какая база данных в данный момент : <b>8000+ матчей</b>\n\n" +
                "\uD83D\uDC41<b> Будущие обновления : </b> \n" +
                "1. <b>Обновляем сбор данных.</b> Увеличиваем базу данных до 50.000 матчей\n" +
                "2. <b>Пользователь выбирает пики.</b> Мы не можем знать какие карты будут сыграны, а это очень важный фактор. " +
                "Карты будете выбирать вы.\n" +
                "3. <b>Платные функции.</b> Успевайте!";
        SendMessage sendMessage = generateMessage(action, message);
        ActionExecutor.send(sendMessage);
    }
}
