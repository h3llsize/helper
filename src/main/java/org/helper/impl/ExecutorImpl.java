package org.helper.impl;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ExecutorImpl {
    void execute(Update update);
}
