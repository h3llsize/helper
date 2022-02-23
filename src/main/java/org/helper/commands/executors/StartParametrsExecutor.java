package org.helper.commands.executors;

import org.helper.commands.handlers.SetPromoHandler;
import org.helper.impl.ExecutorImpl;
import org.helper.user.User;
import org.helper.user.UserStorage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartParametrsExecutor implements ExecutorImpl {
    @Override
    public void execute(Update update) {
        String param = update.getMessage().getText().split(" ")[1];
        User user = UserStorage.getUserByAction(update);


        if (param.startsWith("pr")) {
            new SetPromoHandler(user, param);
        }
        else if (param.startsWith("/")) {
            Message message = update.getMessage();
            message.setText(param);

            update.setMessage(message);
            new MessageExecutor().execute(update);
        }
    }
}
