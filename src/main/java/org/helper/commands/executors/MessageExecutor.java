package org.helper.commands.executors;

import org.helper.commands.launcher.ActionStorage;
import org.helper.impl.ExecutorImpl;
import org.helper.user.User;
import org.helper.user.UserStorage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageExecutor implements ExecutorImpl {
    public void execute(Update update) {
        User user = UserStorage.getUserByAction(update);
        String messageText = update.getMessage().getText();


        if (messageText.startsWith("/")) {
            String command = messageText.split(" ")[0];
            if(user.isAdmin && ActionStorage.adminsHashMap.containsKey(command))
            {
                ActionStorage.getAdminAction(command).exec(update,user);
                user.position = command;
                return;
            }

            if (ActionStorage.actionHashMap.containsKey(command)) {

                ActionStorage.getAction(command).exec(update, user);
                user.position = command;

            } else {
                ActionStorage.getAction("Unknown").exec(update, user);
            }
        }

        else {
            if (ActionStorage.actionHashMap.containsKey(messageText)) {

                ActionStorage.getAction(messageText).exec(update, user);
                user.position = update.getMessage().getText();

            } else {
                ActionStorage.getAction("Unknown").exec(update, user);
            }
        }
    }
}
