package org.helper.impl;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public abstract class IKeyboard {
    public ReplyKeyboardMarkup keyboard;
    public IKeyboard(Update action)
    {
        this.keyboard = createKeyboard(action);
    }
    protected abstract ReplyKeyboardMarkup createKeyboard(Update action);
}
