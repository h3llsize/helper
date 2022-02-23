package org.helper.commands.launcher;

import org.helper.commands.executors.MessageExecutor;
import org.helper.commands.executors.StartParametrsExecutor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.helper.user.User;
import org.helper.user.UserStorage;

public class ActionLauncher {

    public static void execute(Update update) throws Exception{
        if (update.hasMessage()) {
            messageExecute(update);
        }
        else if (update.hasCallbackQuery()) {
            callbackExecute(update);
        }
        else if(update.hasPreCheckoutQuery())
        {
            preCheckoutExecute(update);
        }
    }

    private static void messageExecute(Update update)
    {
        if(update.getMessage().getText().startsWith("/start") && update.getMessage().getText().split(" ").length > 1) {
            StartParametrsExecutor startExecutor = new StartParametrsExecutor();
            startExecutor.execute(update);
            return;
        }

        MessageExecutor executor = new MessageExecutor();
        executor.execute(update);
    }

    private static void callbackExecute(Update update)
    {
        User user = UserStorage.getUserByAction(update.getCallbackQuery());
        if (ActionStorage.callbackHashMap.containsKey(update.getCallbackQuery().getData())) {
            ActionStorage.getCallback(update.getCallbackQuery().getData()).exec(update, user);
        } else {
            ActionStorage.getAction("Unknown").exec(update, user);
        }
    }

    private static void preCheckoutExecute(Update update)
    {

    }
}
