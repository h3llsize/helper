package org.helper.parser;

import org.helper.impl.ICallback;
import org.helper.impl.IUser;
import org.helper.main.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MatchCallback extends ICallback {
    public MatchCallback(String name) {
        super(name);
    }

    @Override
    public void exec(Update action, IUser user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(action.getCallbackQuery().getMessage().getChatId().toString());
        sendMessage.enableHtml(true);

        String info = ParserStorage.headParser.info.get(name);
        String stats = ParserStorage.headParser.stats.get(name);
        String[] statsArray = stats.split(",");

        StringBuilder message = new StringBuilder();
        message.append("<strong>" + name + "</strong>\n")
                .append("<i>" + info.split(",")[1] + "</i>\n\n");

        message.append("<b>" + name.split(" vs ")[0] + " complete stats : </b> \n") ;
        message.append("\uD83E\uDE96 Rank : <b> " + statsArray[0] + "</b> \n");
        message.append("\uD83E\uDDE2 Stand Ins : <b> " + statsArray[64] + "</b> \n");
        message.append("\uD83D\uDCA1 Win Rate : <b> " + statsArray[63].replace("0.","") + "% </b> \n");
            message.append("⚡️ Average opponent ranks : " + new DecimalFormat("#0.00").format(averageStats(new String[] {
                    statsArray[68], statsArray[86], statsArray[102], statsArray[118], statsArray[134],
                    statsArray[150], statsArray[166], statsArray[182], statsArray[198]
            }))).append("\n\n");

        message.append("<b>" + name.split(" vs ")[1] + " complete stats : </b>\n") ;
        message.append("\uD83E\uDE96 Rank : <b> " + statsArray[1] + "</b>\n");
        message.append("\uD83E\uDDE2 Stand Ins : <b> " + statsArray[249] + "</b>\n");
        message.append("\uD83D\uDCA1 Win Rate : <b> " + statsArray[248].replace("0.","") + "% </b>\n");
            message.append("⚡️ Average opponent ranks : " + new DecimalFormat("#0.00").format(averageStats(new String[] {
                    statsArray[271], statsArray[287], statsArray[303], statsArray[319], statsArray[335],
                    statsArray[351], statsArray[367], statsArray[383], statsArray[399]
            }))).append("\n\n");


        message.append("\uD83D\uDCB8 <b> Наш прогноз : Победа команды ");

        if (Integer.parseInt(statsArray[412]) < 1.5)
            message.append(name.split(" vs ")[0]);
        else
            message.append(name.split(" vs ")[1]);

        message.append("</b>\n");
        if (info.split(",")[2].contains("3")) {
            message.append("<pre> Счёт ");

            if (statsArray[412].equals("0"))
                message.append("2 : 0");
            if (statsArray[412].equals("1"))
                message.append("2 : 1");
            if (statsArray[412].equals("2"))
                message.append("1 : 2");
            if (statsArray[412].equals("3"))
                message.append("0 : 2");

            message.append("</pre>");
        }

        InlineKeyboardButton button = new InlineKeyboardButton();
        List<InlineKeyboardButton> row = new ArrayList<>();
        button.setText("Ссылка");
        button.setUrl("http://hltv.org" + info.split(",")[3]);

        row.add(button);
        List<List<InlineKeyboardButton>> listKb = new ArrayList<>();
        listKb.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listKb);

        sendMessage.setText(message.toString());
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        try {
            new Bot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private double averageStats(String[] list)
    {
        double all = 0;
        int count = 0;
        for(int i = 0; i < list.length; i++)
        {
            all += Double.parseDouble(list[i]);
            count++;
        }
        try {
            return all / count;
        } catch (Exception e)
        {
            return 0;
        }
    }
}
