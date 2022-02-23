package org.helper.parser.matcheshltv;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class ParserMethods {
    private static final int RECONNECT_TIME = 500;

    public static Document getPage(String url) throws InterruptedException {
        Thread.sleep(RECONNECT_TIME);
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url),30000);
        } catch (IOException exception) {
            Thread.sleep(3000);
        }
        return doc;
    }

    static Document getHltvPage(String url) throws InterruptedException {
        Thread.sleep(RECONNECT_TIME);
        String completeUrl = "https://www.hltv.org" + url;
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(completeUrl),30000);
        } catch (IOException exception) {
            Thread.sleep(30000);
        }
        return doc;
    }

    static String scoreToPScore(String score)
    {
        if(score.equals("2 - 0"))
            return "0";
        if(score.equals("2 - 1"))
            return "1";
        if(score.equals("1 - 2"))
            return "2";
        if(score.equals("0 - 2"))
            return "3";
        return "0";
    }

    public static LocalDate hltvDateToDate(String hltvDate)
    {
        String[] firstStep = hltvDate.replace("of ", "").split(" ");

        LocalDate localDate = LocalDate.of(Integer.parseInt(firstStep[2]),Integer.parseInt(getMonth(firstStep[1])),Integer.parseInt(firstStep[0].replaceAll("\\D+","")));
        return localDate;
    }

    private static String getMonth(String month) {
        if(month.equals("January"))
            return "01";
        if(month.equals("February"))
            return "02";
        if(month.equals("March"))
            return "03";
        if(month.equals("April"))
            return "04";
        if(month.equals("May"))
            return "05";
        if(month.equals("June"))
            return "06";
        if(month.equals("July"))
            return "07";
        if(month.equals("August"))
            return "08";
        if(month.equals("September"))
            return "09";
        if(month.equals("October"))
            return "10";
        if(month.equals("November"))
            return "11";
        return "12";
    }
}
