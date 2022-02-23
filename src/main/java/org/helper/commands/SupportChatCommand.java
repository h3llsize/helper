package org.helper.commands;

import org.helper.commands.launcher.ActionExecutor;
import org.helper.impl.IMessage;
import org.helper.user.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SupportChatCommand extends IMessage {
    /**
     * С помощью этого, мы получаем имя действия
     * Каждое действие будет содержать своё имя и тип(нажал на кнопку, тыкнул на эмодзи и т.д)
     * После того, как наш пользователь сделает действие, мы будем его сравнивать с имеющимися
     *
     * @param name команды
     */
    public SupportChatCommand(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, User user) {
        String message = "<pre> ☕️ Для чайников </pre> \n" +
                "<b> \uD83E\uDE96 Rank - ранк команды, указанный на HLTV.ORG </b>\n" +
                "<b> \uD83E\uDDE2 Stand Ins - Количество игроков, которые недавно вошли в состав / замены </b>\n" +
                "<b> \uD83D\uDCA1 Win Rate - Винрейт команды \uD83D\uDC81\u200D♀️ </b>\n" +
                "<b> \uD83D\uDD2B Average rank opponents - Средний ранк игроков, против которых она играла </b>" +
                "\n\n" +
                "<pre>⚙️ Советы по использованию бота</pre> \n" +
                "\uD83D\uDC64 1. <b>Если вы видите прогноз с большим коэффицентом, " +
                "то это не означает, что ИЗИ бабки. Сделайте собственный анализ и только тогда делайте ставку.</b>\n" +
                "\uD83D\uDC64 2. <b>Старайтесь не ставить на матчи, где ранки у обеих команд больше 150-200. Там полнейший рандом, который никто не в состоянии предсказать</b>.\n" +
                "\uD83D\uDC64 3. <b>Всегда старайтесь делать свой мини-анализ. Если в будущем у вас будет потребность в этом, то мы готовы запилить полноценный гайд где и на что нужно смотреть.</b>\n" +
                "\uD83D\uDC64 4. <b>Не забывайте следить за новостями. Чтобы понимать ментальное состоянии оппонентов.</b>";
        SendMessage sendMessage = generateMessage(action, message);
        ActionExecutor.send(sendMessage);
    }
}
