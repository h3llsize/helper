package org.helper.impl;

import org.jsoup.nodes.Document;

public abstract class IStats {
    public final StringBuilder statistic = new StringBuilder();
    public final Document page;

    protected IStats(Document page) {
        this.page = page;
        parse();
    }
    public abstract void parse();

}
