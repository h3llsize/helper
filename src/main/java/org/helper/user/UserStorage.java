package org.helper.user;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.helper.impl.IUser;

import java.util.HashMap;

public class UserStorage {
    public static HashMap<String, User> userStorage = new HashMap<>();

    public static User getUserByAction(Update action)
    {
        try {
            String chat_id = action.getMessage().getChatId().toString();

            if (userStorage.containsKey(chat_id))
                return userStorage.get(chat_id);
            else
            {
                User user = new User();
                user.createUser(action);

                userStorage.put(String.valueOf(user.chatId), user);
                return user;
            }
        } catch (Exception e) {

            User user = new User();
            user.createUser(action);

            userStorage.put(String.valueOf(user.chatId), user);
            return user;
        }
    }

    public static User getUserByAction(CallbackQuery callbackQuery)
    {
        try {
            String chat_id = callbackQuery.getMessage().getChatId().toString();

            if (userStorage.containsKey(chat_id))
                return userStorage.get(chat_id);
            else
            {
                User user = new User();
                user.createUser(callbackQuery);

                userStorage.put(String.valueOf(user.chatId), user);
                return user;
            }
        } catch (Exception e) {

            User user = new User();
            user.createUser(callbackQuery);

            userStorage.put(String.valueOf(user.chatId), user);
            return user;
        }
    }

    public static IUser getUserByChatId(long chat_ID)
    {
        String chat_id = chat_ID + "";

        if(userStorage.containsKey(chat_id))
            return userStorage.get(chat_id);

        return null;
    }
}
