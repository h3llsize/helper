package org.helper.main;

import org.helper.database.DBLoader;
import org.helper.parser.ParserStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectMethods extends Thread {
    @Override
    public void run() {
        try {
            waitCommand();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void waitCommand() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String command = bufferedReader.readLine();
        exec(command);

        waitCommand();
    }

    void exec(String command)
    {
        if(command.equals("stop"))
        {
            System.exit(1);
        }

        if(command.equals("help"))
        {
            String help = "ID - Уникальный индефекатор пользователя, CHAT_ID - Айди чата \n\n" +
                    "addAdmin [CHAT_ID] - Добавить админа\n" +
                    "removeAdmin [chat_ID] - Удалить админа\n" +
                    "addSub [CHAT_ID] [DAYS] - Выдать сабку\n" +
                    "removeSub [CHAT_ID] - Забрать сабку\n" +
                    "removeSubById [ID] - Удалить сабку с помощью уникального номера \n" +
                    "getAllAdmins - Получить список всех админов\n +" +
                    "getAllSubs - Получить всех подписчиков\n" +
                    "getUserByName [NAME] - получить имя пользователя из ника\n" +
                    "getUserRefers [ID] - получить имя пользователя из ника";

            System.out.println(help);
        }

        if(command.startsWith("addAdmin"))
        {
            try {
            String[] arg = command.split(" ");
            System.out.println(DBLoader.addAdmin(arg[1]));
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }

        if(command.startsWith("updateMatches"))
        {
            try {
                ParserStorage.updateKeyboard();
                System.out.println("UPDATING");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }

        if(command.startsWith("removeAdmin"))
        {
            try {
            String[] arg = command.split(" ");

            System.out.println(DBLoader.removeAdmin(arg[1]));

            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        if(command.startsWith("getAllUsers"))
        {
            System.out.println(DBLoader.getAllUsers());
        }
    }
}
