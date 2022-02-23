package org.helper.database;

import org.helper.user.User;
import org.helper.user.UserStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DBLoader {

    public static String addAdmin(String chat_id)
    {
        User user = (User) UserStorage.getUserByChatId(Integer.parseInt(chat_id));

        user.giveAdmin();
        return "Админка была выдана!";
    }

    public static String removeAdmin(String chat_id)
    {
        User user = (User) UserStorage.getUserByChatId(Integer.parseInt(chat_id));
        user.removeAdmin();
        return "Подписка была удалена!";
    }

    public static String removeSub(String chat_id)
    {

        User user = (User) UserStorage.getUserByChatId(Integer.parseInt(chat_id));
        user.removeSub();
        return "Подписка была удалена!";
    }

    public static String getAllUsers()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Statement statement = Connection.getStatement();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Subs");

            while (resultSet.next()){
                stringBuilder.append(resultSet.getInt("id")).append(" | ").
                        append(resultSet.getInt("chat_id")).append(" | ")
                        .append("\n & \n");
                stringBuilder.append(resultSet.getDate("sub_date").toLocalDate()).append(" | ").append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static String getAllAdmins()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Statement statement = Connection.getStatement();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Subs WHERE adm = true");

            while (resultSet.next()){
                stringBuilder.append(resultSet.getInt("id")).append(" | ").
                        append(resultSet.getInt("chat_id")).append(" | ")
                        .append(resultSet.getDate("sub_date").toLocalDate())
                        .append("\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
