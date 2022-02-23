package org.helper.parser.matcheshltv;

import org.helper.impl.IStats;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;

import static org.helper.parser.matcheshltv.ParserMethods.getPage;


public class TeamStatsParser extends IStats {
    private static boolean DEBUG = true;
    protected TeamStatsParser(Document page) {
        super(page);
    }

    @Override
    public void parse() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder playerStats = new StringBuilder();
            StringBuilder teamRanks = new StringBuilder();

            Elements elements = page.select("div[class=standard-box teamsBox]").select("div[class=team]").select("a");

            LocalDate date = LocalDate.now();

            for(Element element : elements)
            {
                String url = "https://www.hltv.org" + element.attr("href")
                        +"?startDate=" + date.minusDays(60) + "&endDate=" + date;

                Document teamStats = getPage(url.replace("team", "stats/teams"));
                Document teamPage = getPage(url);

                playerStats.append(new PlayersStatsParser(teamPage).statistic);
                stringBuilder.append(getAllTeamStats(teamStats, url.replace("team", "stats/teams")));
                teamRanks.append(getTeamRank(teamPage).replace("World ranking-","400") + ",");

            }

            statistic.append(teamRanks).append(playerStats).append(stringBuilder);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getTeamRank(Document teamPage)
    {
        String rank = teamPage.select("div[class=profile-team-stats-container]").select("div[class=profile-team-stat]").first().text().replace("World ranking#", "");


        if(DEBUG)
            System.out.println("RANK : " + teamPage.select("div[class=profile-team-stats-container]").select("div[class=profile-team-stat]").first().text().replace("World ranking#", ""));
        return rank;
    }

    private StringBuilder getAllTeamStats(Document teamPage, String url)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String mapTeamPageUrl = url.replace("teams", "teams/maps");
            Document document = getPage(mapTeamPageUrl);
            stringBuilder.append(getAllMapStats(document));

            Elements elements = teamPage.select("div[class=col standard-box big-padding]");
            stringBuilder.append(winRateStats(elements));

            String playersInTeamPageUrl = url.replace("teams", "teams/players");
            Document playersPageInTeam = getPage(playersInTeamPageUrl);
            Elements players = playersPageInTeam.select("div[class=stats-section]").select("table[class=stats-table player-ratings-table]").select("tr");
            stringBuilder.append(getStandIns(players));

            String matchStatsInTeam = url.replace("teams", "teams/matches");

            String teamName = teamPage.select("div[class=sidebar-box ]").select("span[class=context-item-name]").text();

            String opponent = new OpponentRanksParser(teamName, getPage(matchStatsInTeam)).statistic.toString();
            if(DEBUG)
                System.out.println("Opponent : " + opponent);

            stringBuilder.append(opponent);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    private StringBuilder getStandIns(Elements players)
    {
        StringBuilder stringBuilder = new StringBuilder();
        int standIN = 6;

        for(Element element : players)
            standIN--;

        if(DEBUG)
            System.out.println("STANDIN = " + standIN);

        stringBuilder.append(standIN + ",");
        return stringBuilder;
    }

    private StringBuilder winRateStats(Elements elements)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Element element : elements)
        {
            if(element.select("div[class=small-label-below]").text().equals("Wins / draws / losses"))
            {
                String winrate = element.select("div[class=large-strong]").text();
                String[] stats = winrate.split(" / ");

                double winr = Double.parseDouble(stats[0]) / (Double.parseDouble(stats[0]) + Double.parseDouble(stats[2]));
                String result = String.format("%.2f",winr).replace(",",".");

                stringBuilder.append(result + ",");

                if(DEBUG)
                {
                    System.out.println("WINRATE% : " + result);
                }
            }
        }
        return stringBuilder;
    }

    private StringBuilder getAllMapStats(Document mapPage)
    {
        StringBuilder stringBuilder = new StringBuilder();

        String mir = "0,0.0,0,";
        String dust = "0,0.0,0,";
        String inf = "0,0.0,0,";
        String over = "0,0.0,0,";
        String nuke = "0,0.0,0,";
        String vert = "0,0.0,0,";
        String anc = "0,0.0,0,";

        Elements elements = mapPage.select("div[class=two-grid]").select("div[class=col]");
        for(Element element : elements)
        {
            if(element.text().startsWith("Mirage"))
            {
                StringBuilder mapStats = new StringBuilder();
                Elements stats = element.select("div[class=stats-row]");
                for(Element cs : stats)
                {
                    if(cs.text().startsWith("Wins / draws / losses"))
                    {
                        String res = cs.text().replace("Wins / draws / losses","");
                        String[] maps = res.split(" / ");

                        int totalMaps = Integer.parseInt(maps[0]) + Integer.parseInt(maps[1]) + Integer.parseInt(maps[2]);
                        mapStats.append(totalMaps + ",");
                    }

                    if(cs.text().startsWith("Win rate"))
                    {
                        String wr = cs.text().replace("Win rate","").replace("%","");
                        mapStats.append(wr + ",");
                    }
                    if(cs.text().startsWith("Total rounds"))
                    {
                        String total = cs.text().replace("Total rounds","");
                        mapStats.append(total + ",");
                    }
                    mir = mapStats.toString();
                }
            }
            if(element.text().startsWith("Dust2"))
            {
                StringBuilder mapStats = new StringBuilder();
                Elements stats = element.select("div[class=stats-row]");
                for(Element cs : stats)
                {
                    if(cs.text().startsWith("Wins / draws / losses"))
                    {
                        String res = cs.text().replace("Wins / draws / losses","");
                        String[] maps = res.split(" / ");

                        int totalMaps = Integer.parseInt(maps[0]) + Integer.parseInt(maps[1]) + Integer.parseInt(maps[2]);
                        mapStats.append(totalMaps + ",");
                    }

                    if(cs.text().startsWith("Win rate"))
                    {
                        String wr = cs.text().replace("Win rate","").replace("%","");
                        mapStats.append(wr + ",");
                    }
                    if(cs.text().startsWith("Total rounds"))
                    {
                        String total = cs.text().replace("Total rounds","");
                        mapStats.append(total + ",");
                    }
                    dust = mapStats.toString();
                }
            }
            if(element.text().startsWith("Inferno"))
            {
                StringBuilder mapStats = new StringBuilder();
                Elements stats = element.select("div[class=stats-row]");
                for(Element cs : stats)
                {
                    if(cs.text().startsWith("Wins / draws / losses"))
                    {
                        String res = cs.text().replace("Wins / draws / losses","");
                        String[] maps = res.split(" / ");

                        int totalMaps = Integer.parseInt(maps[0]) + Integer.parseInt(maps[1]) + Integer.parseInt(maps[2]);
                        mapStats.append(totalMaps + ",");
                    }

                    if(cs.text().startsWith("Win rate"))
                    {
                        String wr = cs.text().replace("Win rate","").replace("%","");
                        mapStats.append(wr + ",");
                    }
                    if(cs.text().startsWith("Total rounds"))
                    {
                        String total = cs.text().replace("Total rounds","");
                        mapStats.append(total + ",");
                    }
                    inf = mapStats.toString();
                }
            }
            if(element.text().startsWith("Overpass"))
            {
                StringBuilder mapStats = new StringBuilder();
                Elements stats = element.select("div[class=stats-row]");
                for(Element cs : stats)
                {
                    if(cs.text().startsWith("Wins / draws / losses"))
                    {
                        String res = cs.text().replace("Wins / draws / losses","");
                        String[] maps = res.split(" / ");

                        int totalMaps = Integer.parseInt(maps[0]) + Integer.parseInt(maps[1]) + Integer.parseInt(maps[2]);
                        mapStats.append(totalMaps + ",");
                    }

                    if(cs.text().startsWith("Win rate"))
                    {
                        String wr = cs.text().replace("Win rate","").replace("%","");
                        mapStats.append(wr + ",");
                    }
                    if(cs.text().startsWith("Total rounds"))
                    {
                        String total = cs.text().replace("Total rounds","");
                        mapStats.append(total + ",");
                    }
                    over = mapStats.toString();
                }
            }
            if(element.text().startsWith("Nuke"))
            {
                StringBuilder mapStats = new StringBuilder();
                Elements stats = element.select("div[class=stats-row]");
                for(Element cs : stats)
                {
                    if(cs.text().startsWith("Wins / draws / losses"))
                    {
                        String res = cs.text().replace("Wins / draws / losses","");
                        String[] maps = res.split(" / ");

                        int totalMaps = Integer.parseInt(maps[0]) + Integer.parseInt(maps[1]) + Integer.parseInt(maps[2]);
                        mapStats.append(totalMaps + ",");
                    }

                    if(cs.text().startsWith("Win rate"))
                    {
                        String wr = cs.text().replace("Win rate","").replace("%","");
                        mapStats.append(wr + ",");
                    }
                    if(cs.text().startsWith("Total rounds"))
                    {
                        String total = cs.text().replace("Total rounds","");
                        mapStats.append(total + ",");
                    }
                    nuke = mapStats.toString();
                }
            }
            if(element.text().startsWith("Vertigo") || element.text().startsWith("Cache"))
            {
                StringBuilder mapStats = new StringBuilder();
                Elements stats = element.select("div[class=stats-row]");
                for(Element cs : stats)
                {
                    if(cs.text().startsWith("Wins / draws / losses"))
                    {
                        String res = cs.text().replace("Wins / draws / losses","");
                        String[] maps = res.split(" / ");

                        int totalMaps = Integer.parseInt(maps[0]) + Integer.parseInt(maps[1]) + Integer.parseInt(maps[2]);
                        mapStats.append(totalMaps + ",");
                    }

                    if(cs.text().startsWith("Win rate"))
                    {
                        String wr = cs.text().replace("Win rate","").replace("%","");
                        mapStats.append(wr + ",");
                    }
                    if(cs.text().startsWith("Total rounds"))
                    {
                        String total = cs.text().replace("Total rounds","");
                        mapStats.append(total + ",");
                    }
                    vert = mapStats.toString();
                }
            }
            if(element.text().startsWith("Ancient") || element.text().startsWith("Train"))
            {
                StringBuilder mapStats = new StringBuilder();
                Elements stats = element.select("div[class=stats-row]");
                for(Element cs : stats)
                {
                    if(cs.text().startsWith("Wins / draws / losses"))
                    {
                        String res = cs.text().replace("Wins / draws / losses","");
                        String[] maps = res.split(" / ");

                        int totalMaps = Integer.parseInt(maps[0]) + Integer.parseInt(maps[1]) + Integer.parseInt(maps[2]);
                        mapStats.append(totalMaps + ",");
                    }

                    if(cs.text().startsWith("Win rate"))
                    {
                        String wr = cs.text().replace("Win rate","").replace("%","");
                        mapStats.append(wr + ",");
                    }
                    if(cs.text().startsWith("Total rounds"))
                    {
                        String total = cs.text().replace("Total rounds","");
                        mapStats.append(total + ",");
                    }
                    anc = mapStats.toString();
                }
            }
        }

        stringBuilder.append(mir + dust + inf + over + nuke + vert + anc);

        if(DEBUG) {
            System.out.println();
            System.out.println("|    MIRAGE    |    DUST    |    INFERNO    |    OVERPASS    |    NUKE    |    VERTIGO    |    ANCIENT    |");
            String[] strings = stringBuilder.toString().split(",");
            System.out.println("|MAPS WR ROUNDS|MAPS WR ROUNDS|MAPS WR ROUNDS|MAPS WR ROUNDS|MAPS WR ROUNDS|MAPS WR ROUNDS|MAPS WR ROUNDS|");
            System.out.println("  " + strings[0] + "  " + strings[1] + " " + strings[2] + "   " + strings[3] + "   " + strings[4] + " " + strings[5] + "    " + strings[6] + "  " +
                    strings[7] + "  " + strings[8] + "  " + strings[9] + "  " + strings[10] + "  " + strings[11] + "    " + strings[12] + "  " + strings[13] + "  " + strings[14] + "    " +
                    strings[15] + "  " + strings[16] + "  " + strings[17] + "    " + strings[18] + "  " + strings[19] + "  " + strings[20]);
            System.out.println();
        }
        return stringBuilder;
    }
}
