package org.helper.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String botUserName;
    public static String botToken;
    public static String hostDatabase;
    public static String userDatabase;
    public static String passDatabase;
    public static String channelId;

    public static void Initialize()
    {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/config.properties"));

            botUserName = prop.getProperty("bot_name");
            botToken = prop.getProperty("bot_token");

            hostDatabase = prop.getProperty("data_host");
            userDatabase = prop.getProperty("data_user");
            passDatabase = prop.getProperty("data_pass");
            channelId = prop.getProperty("channel_id");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
