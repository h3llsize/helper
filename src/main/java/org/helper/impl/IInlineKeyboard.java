package org.helper.impl;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public abstract class IInlineKeyboard {
    public InlineKeyboardMarkup keyboard;
    public IInlineKeyboard(Update action)
    {
        this.keyboard = createKeyboard(action);
    }
    protected abstract InlineKeyboardMarkup createKeyboard(Update action);
}
