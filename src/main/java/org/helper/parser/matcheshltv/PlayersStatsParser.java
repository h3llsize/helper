package org.helper.parser.matcheshltv;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.helper.impl.IStats;

import java.time.LocalDate;

import static org.helper.parser.matcheshltv.ParserMethods.getPage;

public class PlayersStatsParser extends IStats {
    private static final int daysBefore = 60;
    private static boolean DEBUG = true;
    public PlayersStatsParser(Document page)
    {
        super(page);
    }

    @Override
    public void parse() {
        LocalDate date = LocalDate.now();

        Elements elements = page.select("div[class=bodyshot-team-bg]").select("a");

        int i = 0;
        for (Element element : elements)
        {
            String url = element.select("a").attr("href");
            String completeUrl = "https://www.hltv.org" + url.replace("player","stats/players") +
                    "?startDate=" + date.minusDays(daysBefore) + "&endDate=" + date;


            if (completeUrl.contains("team")) { i=0; continue; }
            i++;
            if (i>5) continue;

            try {
                statistic.append(getPlayerStats(completeUrl));
            } catch (Exception ex)
            {
                continue;
            }
        }

    }

    private String getPlayerStats(String url)
    {
        StringBuilder completeStats = new StringBuilder();
        Document playerPage = null;
        try {
            playerPage = getPage(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(DEBUG) {
            System.out.println("name : " + playerPage.select("h1[class=summaryNickname text-ellipsis]").text());
            System.out.println("HS% | KD | ROUNDS | HLTV 2.0");
        }
        Elements elements = playerPage.select("div[class=stats-row]");
        for (Element element : elements)
        {
            String elementText = element.text();
            if(elementText.contains("Rating 2.0"))
            {
                String stats = elementText.replace("Rating 2.0","");
                if(DEBUG) System.out.println(" " + stats + " ");

                completeStats.append(stats + ",");
            }
            if(elementText.contains("K/D Ratio"))
            {
                String stats = elementText.replace("K/D Ratio","");
                if(DEBUG) System.out.print(" " + stats + " ");
                completeStats.append(stats + ",");
            }
            if(elementText.contains("Headshot"))
            {
                String stats = elementText.replace("Headshot %","");
                if(DEBUG) System.out.print(stats + " ");

                completeStats.append(elementText.replace("Headshot %","").replace("%","") + ",");
            }
            if(elementText.contains("Rounds"))
            {
                String stats = elementText.replace("Rounds played","");
                if(DEBUG) System.out.print(" " + stats + " ");
                completeStats.append(stats + ",");
            }
        }
        return completeStats.toString();
    }
}
