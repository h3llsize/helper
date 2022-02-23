package org.helper.database;

import org.helper.main.Config;

import java.sql.DriverManager;
import java.sql.Statement;

public class Connection {

    private static String host;
    private static String user;
    private static String pass;
    private static Statement statement;

    public static void Initialize() {
        host = Config.hostDatabase;
        user = Config.userDatabase;
        pass = Config.passDatabase;

        System.out.println("DATABASE PROPERTIES INITIALIZE");

        connect();
    }


    public static void connect()
    {
        java.sql.Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(host,user,pass);
            statement = c.createStatement();
            statement.execute("CREATE SEQUENCE IF NOT EXISTS global_seq START WITH 10000;");
            statement.execute("CREATE SEQUENCE IF NOT EXISTS grenade_seq START WITH 1;");

            statement.execute("CREATE TABLE IF NOT EXISTS Subs" +
                    "(   id         INTEGER PRIMARY KEY DEFAULT(nextval('global_seq'))," +
                    "    chat_id    INTEGER UNIQUE                NOT NULL," +
                    "    sub        boolean default(false)        not null," +
                    "    sub_date   TIMESTAMP                     without time zone," +
                    "    adm        boolean default(false)        not null," +
                    "    refer      INTEGER DEFAULT(0)            NOT NULL," +
                    "    balance    INTEGER DEFAULT(0)            not null," +
                    "    promo      VARCHAR UNIQUE" +
                    ");");

            statement.execute("CREATE TABLE IF NOT EXISTS Grenades" +
                    "(   id  INTEGER PRIMARY KEY DEFAULT(nextval('grenade_seq'))," +
                    "    map VARCHAR NOT NULL" +
                    "    name VARCHAR NOT NULL" +
                    "    desc VARCHAR NOT NULL" +
                    "    path VARCHAR NOT NULL);");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
            return statement;
    }
}
