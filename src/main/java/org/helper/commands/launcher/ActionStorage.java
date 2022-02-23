package org.helper.commands.launcher;


import org.helper.commands.*;
import org.helper.commands.admin.GetAllUsersAdminCommand;
import org.helper.commands.callbacks.PreviousPageCallback;
import org.helper.commands.callbacks.nextPageCallback;
import org.helper.impl.IAdmin;
import org.helper.impl.IMessage;
import org.helper.impl.ICallback;

import java.util.HashMap;

public class ActionStorage {
    public static final HashMap<String, IMessage> actionHashMap = new HashMap<String, IMessage>();
    public static final HashMap<String, ICallback> callbackHashMap = new HashMap<String, ICallback>();
    public static final HashMap<String, IAdmin> adminsHashMap = new HashMap<>();

    static {
        new HelloChatCommand("/hello");
        new UnknownChatCommand("Unknown");
        new MatchesChatCommand("/matches");
        new StartChatCommand("/start");
        new SupportChatCommand("/support");
        new DonateChatCommand("/donate");
        new AboutUsChatCommand("/about");
        new nextPageCallback("nextPage");
        new PreviousPageCallback("previousPage");

        new GetAllUsersAdminCommand("/users");
    }


    public static IMessage getAction(String command)
    {
        return actionHashMap.get(command);
    }
    public static ICallback getCallback(String command)
    {
        return callbackHashMap.get(command);
    }
    public static IAdmin getAdminAction(String command)
    {
        return adminsHashMap.get(command);
    }
    public static void addMessageAction(IMessage action, String commandName)
    {
        actionHashMap.put(commandName, action);
    }
    public static void addCallback(ICallback action, String commandName)
    {
        callbackHashMap.put(commandName, action);
    }
    public static void addAdminAction(IAdmin action, String commandName) { adminsHashMap.put(commandName, action);
    }

}
