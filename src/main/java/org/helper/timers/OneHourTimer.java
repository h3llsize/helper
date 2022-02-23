package org.helper.timers;

import org.helper.parser.ParserStorage;
import org.helper.user.UserStorage;

import java.time.LocalDate;

public class OneHourTimer extends Thread{

    @Override
    public void run() {
        while (true)
        {
            try {
                ParserStorage.updateKeyboard();

                Thread.sleep(1000*720*60);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
