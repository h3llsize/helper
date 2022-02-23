package org.helper.parser;

import org.helper.commands.launcher.ActionStorage;
import org.helper.impl.ICallback;
import org.helper.parser.matcheshltv.HeadParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ParserStorage {
    public static ArrayList<InlineKeyboardMarkup> inlineKeyboardMarkups = new ArrayList<>();
    public static ArrayList<ICallback> matchesList = new ArrayList<>();
    public static HeadParser headParser;
    
    public static void updateKeyboard()
    {
        destroyAllOldCallbacks();

        inlineKeyboardMarkups = new ArrayList<>();

        headParser = new HeadParser();

    }

    public static void createKeyboard()
    {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        int i = 1;
        for(String key : headParser.stats.keySet())
        {
            System.out.println("KEY is " + key);

            InlineKeyboardButton match = new InlineKeyboardButton();
            match.setText(headParser.info.get(key).split(",")[0]);
            match.setCallbackData(headParser.info.get(key).split(",")[0]);

            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(match);

            matchesList.add(new MatchCallback(headParser.info.get(key).split(",")[0]));

            keyboard.add(row);
            i++;
            if(i % 5 == 0)
            {
                List<InlineKeyboardButton> pageRow = new ArrayList<>();

                InlineKeyboardButton previousPage = new InlineKeyboardButton();
                previousPage.setText("   <<   ");
                previousPage.setCallbackData("previousPage");
                pageRow.add(previousPage);

                InlineKeyboardButton nextPage = new InlineKeyboardButton();
                nextPage.setText("   >>   ");
                nextPage.setCallbackData("nextPage");

                pageRow.add(nextPage);
                keyboard.add(pageRow);

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(keyboard);
                inlineKeyboardMarkups.add(inlineKeyboardMarkup);

                keyboard = new ArrayList<>();
            }
        }
    }

    public static void destroyAllOldCallbacks()
    {
        if(matchesList.isEmpty()) return;

        matchesList.forEach(s -> {
            ActionStorage.callbackHashMap.remove(s.name);
        });
    }
}
