package org.helper.parser.matcheshltv;

import org.helper.weka.WekaStorage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.helper.parser.ParserStorage;

import java.util.HashMap;

public class HeadParser extends Thread {
    public HashMap<String,String> stats = new HashMap<>();
    public HashMap<String,String> info = new HashMap<>();


    public HeadParser() {
        start();
    }

    @Override
    public void run() {
            try {
                System.out.println("start");
                Document page = ParserMethods.getHltvPage("/matches");
                Elements elements = page.select("div[class=upcomingMatchesContainer]").select("div[class=upcomingMatchesSection]").select("a[class=match a-reset]");

                for (Element element : elements) {
                    StringBuilder allMatchStats = new StringBuilder();
                    Document matchPage = ParserMethods.getHltvPage(element.attr("href"));

                    System.out.println("MATCH URL : " + element.attr("href"));

                    String name = new TeamNameParser(matchPage).statistic.toString();

                    allMatchStats.append(new TeamStatsParser(matchPage).statistic);
                    System.out.println(allMatchStats);

                    System.out.println("\n\n ALL MATCH STATS LENGHT : " + allMatchStats.toString().split(",").length + "\n\n");

                    if(allMatchStats.toString().split(",").length == 412) {
                        info.put(name.split(",")[0],name + "," + element.attr("href"));
                        stats.put(name.split(",")[0], allMatchStats.append(WekaStorage.getPrediction(allMatchStats.toString() + "0")).toString());
                    }
                    else System.out.println("INVALID MATCH");
                }
                ParserStorage.createKeyboard();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
