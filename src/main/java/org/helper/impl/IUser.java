package org.helper.impl;

import org.glassfish.grizzly.http.server.StaticHttpHandler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class IUser {
    public long chatId;
    public boolean isSubscriber;
    public LocalDate subDate;
    public boolean isAdmin;
    public long inviterId;
    public int balance;
    public String promo;
    public boolean isUseTrial;
    public String position;
    public int currentMatchPage;

    public final ArrayList<Long> refersChatID;

    public IUser()
    {
        refersChatID = new ArrayList<>();
    }

    public IUser setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public IUser setSubscriber(boolean subscriber) {
        isSubscriber = subscriber;
        return this;
    }

    public IUser setSubDate(LocalDate subDate) {
        this.subDate = subDate;
        return this;
    }

    public IUser setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }

    public IUser setInviterId(long inviterId) {
        this.inviterId = inviterId;
        return this;
    }

    public IUser setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public IUser setPromo(String promo) {
        this.promo = promo;
        return this;
    }

    public IUser setUseTrial(boolean useTrial) {
        isUseTrial = useTrial;
        return this;
    }

    public IUser setPosition(String position) {
        this.position = position;
        return this;
    }

    public abstract void giveSub(int days);

    public abstract void giveAdmin();

    public abstract void removeSub();

    public abstract void useTrial();

    public abstract void removeAdmin();

    public abstract String giveBalance(int count);

    public abstract String buyBalance(int count);

    public abstract boolean isEnoughMoney(int count);

    public abstract void addRefer(long chat_id);

    public abstract void generatePromo() throws SQLException;

    public abstract void setReferal(String promo) throws Exception;

}
