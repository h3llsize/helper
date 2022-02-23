package org.helper.parser;

import org.helper.impl.IUser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MatchesKeyboard {
    public int page;
    public MatchesKeyboard(int page, IUser user)
    {
            this.page = page;
    }

    public InlineKeyboardMarkup createKeyboard() {
       return ParserStorage.inlineKeyboardMarkups.get(page);
    }
}
