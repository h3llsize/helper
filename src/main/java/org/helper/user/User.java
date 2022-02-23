package org.helper.user;

import org.telegram.telegrambots.meta.api.methods.groupadministration.KickChatMember;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.helper.database.Connection;
import org.helper.impl.IUser;
import org.helper.main.Bot;
import org.helper.main.Config;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class User extends IUser {


    @Override
    public void giveSub(int days) {
        isSubscriber = true;
        LocalDate sub;
        if(LocalDate.now().isBefore(subDate))
        {
            sub = subDate.plusDays(days);
        }
        else
        {
            sub = LocalDate.now().plusDays(days);
        }
        subDate = sub;
        try {
            Connection.getStatement().execute("UPDATE Subs SET sub_date = '" + subDate + "' where chat_id = " + chatId);
            Connection.getStatement().execute("UPDATE Subs SET sub = true where chat_id = " + chatId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void giveAdmin() {
        isAdmin = true;
        try {
            Connection.getStatement().execute("UPDATE Subs SET adm = true where chat_id = " + chatId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeSub() {
        isSubscriber = false;
       KickChatMember kickChatMember = new KickChatMember();
       kickChatMember.setChatId(Config.channelId);
       kickChatMember.setUserId(chatId);

        try {
            Connection.getStatement().execute("UPDATE Subs SET sub_date = '" + LocalDate.now() + "' where chat_id = " + chatId);
            Connection.getStatement().execute("UPDATE Subs SET sub = false where chat_id = " + chatId);

            new Bot().execute(kickChatMember);

        } catch (SQLException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void useTrial() {
        isUseTrial = true;
        try {
            Connection.getStatement().execute("UPDATE Subs SET use_trial = true where chat_id = " + chatId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAdmin() {
        isAdmin = false;
        try {
            Connection.getStatement().execute("UPDATE Subs SET adm = false where chat_id = " + chatId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String giveBalance(int count) {
        balance += count;
        try {
            Connection.getStatement().execute("UPDATE Subs SET balance = " + balance + " where chat_id = " + chatId);
            return "Успешно выдан баланс!";
        } catch (SQLException e) {
            return "Ошибка в выдаче баланса!";
        }
    }

    @Override
    public String buyBalance(int count) {

        giveBalance(count);

        if(inviterId != 0)
            UserStorage.getUserByChatId(inviterId).giveBalance(count/10);

        try {
            Connection.getStatement().execute("UPDATE Subs SET balance = " + balance + " where chat_id = " + chatId);
            return "Успешно выдан баланс!";
        } catch (SQLException e) {
            return "Ошибка в выдаче баланса!";
        }
    }


    @Override
    public boolean isEnoughMoney(int count) {
        return count < balance;
    }

    @Override
    public void addRefer(long chat_id) {
        refersChatID.add(chat_id);
    }

    @Override
    public void generatePromo() {

        if(promo != null) return;

        promo = "pr" + chatId;
        try {
            Connection.getStatement().execute("UPDATE Subs SET promo = '" + promo + "' where chat_id = " + chatId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setReferal(String promo) throws Exception {
        System.out.println(promo);
        for (IUser s : UserStorage.userStorage.values()) {
            System.out.println(s.promo);
            if (s.promo.equals(promo) && !s.promo.equals(this.promo)) {
                inviterId = s.chatId;
                Connection.getStatement().execute("UPDATE Subs SET refer = " + s.chatId + " where chat_id = " + chatId);
                return;
            }
        }
        throw new Exception("Promo not found");
    }


    private void createUser()
    {
        Statement statement = Connection.getStatement();
        try {
            statement.execute("INSERT INTO Subs (CHAT_ID, SUB, SUB_DATE, ADM, refer, balance)" +
                    "VALUES(" + chatId + "," + isSubscriber + ",'" + subDate + "'," + isAdmin + "," + inviterId + "," + balance + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(CallbackQuery callbackQuery)
    {
        Message message = callbackQuery.getMessage();

        setChatId(message.getChatId());
        setSubscriber(false);
        setSubDate(LocalDate.now());
        setAdmin(false);
        setBalance(0);
        setUseTrial(false);
        setPosition("");
        setInviterId(0);

        generatePromo();

        createUser();
    }

    public void createUser(Update update)
    {
        Message message = update.getMessage();

        setChatId(message.getChatId());
        setSubscriber(false);
        setSubDate(LocalDate.now());
        setAdmin(false);
        setBalance(0);
        setUseTrial(false);
        setPosition("");
        setInviterId(0);

        generatePromo();

        createUser();
    }
}
