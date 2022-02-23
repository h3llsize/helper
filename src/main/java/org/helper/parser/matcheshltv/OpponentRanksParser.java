package org.helper.parser.matcheshltv;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static org.helper.parser.matcheshltv.ParserMethods.getHltvPage;

public class OpponentRanksParser {
    public final StringBuilder statistic = new StringBuilder();
    private final String name;

    public OpponentRanksParser(String name, Document doc)
    {
        this.name = name;
        parseStats(doc);
    }

    private void parseStats(Document page)
    {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Elements groups = page.select("table[class=stats-table no-sort]").select("tr");
        String[] results = new String[] {
                //maps 1 - mirage , 2 - dust, 3 - inferno, 4 - over, 5 - nuke, 6 - vert, 7 - ancient
                // us team score, opponent team score, rank 1 team, rank 2 team, score 1 map, score 2 map, score 3 map /0 0, pick, pick, decider, hltv 2.0 1/2/3/4/5 player
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,",
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"
        };

        int i = 0;
        for (Element element : groups)
        {
            try {
                Thread.sleep(10);

                if(i == 9) break;

                if(element.attr("class").equals("group-2 first") || element.attr("class").equals("group-1 first")) {
                    String hr = element.select("td[class=time]").select("a").attr("href");

                    String href = getHltvPage(hr).
                            select("div[class=match-info-box-con]").select("a[class=match-page-link button]").attr("href");

                    Document statsPage = getHltvPage(href);

                    if(statsPage.select("div[class=padding preformatted-text]").text().startsWith("Best of 1"))
                    {
                        continue;
                    }
                    System.out.println(href);
                    results[i] = getAllMatchStats(statsPage);

                    i++;
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        for(int v = 0; v < results.length; v++)
        {
            statistic.append(results[v]);
        }
    }

    private String getAllMatchStats(Document doc)
    {
        String[] allMatchStats = new String[] {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};


        Elements hltvrating = doc.select("div[class=matchstats]").select("div[id=all-content]");
        for(Element element : hltvrating)
        {
            Elements tables = element.select("table[class=table totalstats]");
            for(Element table : tables)
            {
                if(!table.select("tr[class=header-row]").select("a").text().equalsIgnoreCase(this.name)) continue;

                Elements hltv = tables.select("td[class=rating text-center]");

                int playerN = 0;
                for(Element player : hltv)
                {
                    if(playerN == 6) break;

                    playerN++;
                    if(playerN == 1) continue;

                    allMatchStats[11 + playerN] = player.text();
                }
            }
        }

        Elements teamRanks = doc.select("div[class=lineup standard-box]");

        for(Element element : teamRanks)
        {
            if(element.select("div[class=flex-align-center]").select("a").text().equalsIgnoreCase(this.name))
            {
                allMatchStats[2] = element.select("div[class=teamRanking]").text()
                        .replace("World rank: #","").replace("Unranked","400");
            }
            else {
                allMatchStats[3] = element.select("div[class=teamRanking]").text()
                        .replace("World rank: #","").replace("Unranked","400");
            }
        }

        //score
        Elements stats = doc.select("div[class=standard-box teamsBox]").select("div[class=team]");
        for(Element element : stats)
        {
            if(element.select("div").select("div[class=teamName]").text().equalsIgnoreCase(this.name))
            {
                allMatchStats[0] = element.select("div").last().text();
            }
            else {
                allMatchStats[1] = element.select("div").last().text();
            }
        }

        //1,2,3 maps all stats
        Elements maps = doc.select("div[class=mapholder]");
        int mapN = 0;
        for(Element map : maps)
        {
            String score = map.select("div[class=results-team-score]").text();

            allMatchStats[4 + (mapN * 2)] = score.split(" ")[0].replace("-","0");
            allMatchStats[5 + (mapN * 2)] = score.split(" ")[1].replace("-","0");

            String mapName = mapToNumber(map.select("div[class=mapname]").text());
            allMatchStats[10 + mapN] = mapName;

            mapN++;
        }

        StringBuilder returnBuilder = new StringBuilder();
        for(int i = 0; i < allMatchStats.length; i++)
        {
            returnBuilder.append(allMatchStats[i] + ",");
        }

        System.out.println(returnBuilder);
        return returnBuilder.toString();
    }

    private String mapToNumber(String mapname)
    {
        if(mapname.equalsIgnoreCase("Mirage"))
            return "1";
        if(mapname.equalsIgnoreCase("Dust2"))
            return "2";
        if(mapname.equalsIgnoreCase("Inferno"))
            return "3";
        if(mapname.equalsIgnoreCase("Overpass"))
            return "4";
        if(mapname.equalsIgnoreCase("Nuke"))
            return "5";
        if(mapname.equalsIgnoreCase("Vertigo") || mapname.equalsIgnoreCase("Cache"))
            return "6";
        if(mapname.equalsIgnoreCase("Ancient") || mapname.equalsIgnoreCase("Train"))
            return "7";
        return "-1";
    }
}
