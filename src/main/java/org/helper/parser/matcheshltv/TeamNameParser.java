package org.helper.parser.matcheshltv;

import org.jsoup.nodes.Document;
import org.helper.impl.IStats;

public class TeamNameParser extends IStats {
    protected TeamNameParser(Document page) {
        super(page);
    }

    public void parse()
    {
        page.select("div[class=standard-box teamsBox]").forEach(el -> {

            statistic.append(el.select("div[class=teamName]").get(0).text() + " vs " + el.select("div[class=teamName]").get(1).text() + ",");

            statistic.append(el.select("div[class=event text-ellipsis]").select("a").text() + ",");

        });

        statistic.append(page.select("div[class=standard-box veto-box]").text().split("\\(")[0]);

        System.out.println(statistic);
    }
}
