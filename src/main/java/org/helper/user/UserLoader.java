package org.helper.user;

import org.helper.database.Connection;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class UserLoader {
    public static void Initialize()
    {
        Statement statement = Connection.getStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Subs");

            while (resultSet.next())
            {
                long chatId = resultSet.getLong("CHAT_ID");
                boolean isSubscriber = resultSet.getBoolean("sub");
                LocalDate subDate = resultSet.getDate("sub_date").toLocalDate();
                boolean isAdmin = resultSet.getBoolean("adm");
                int inviterID = resultSet.getInt("refer");
                int balance = resultSet.getInt("balance");
                String promo = resultSet.getString("promo");

                User user = new User();
                user.setChatId(chatId).setSubscriber(isSubscriber).setSubDate(subDate)
                        .setAdmin(isAdmin).setInviterId(inviterID).setBalance(balance).setPromo(promo);

                UserStorage.userStorage.put(String.valueOf(user.chatId), user);
            }

            resultSet = statement.executeQuery("SELECT * FROM Subs");

            while (resultSet.next())
            {

                long refer = resultSet.getLong("refer");

                if(refer != 0)
                UserStorage.getUserByChatId(refer).addRefer(resultSet.getLong("CHAT_ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
