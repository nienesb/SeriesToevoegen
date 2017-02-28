package com.example.jboeser.seriestoevoegen;

import java.io.Serializable;

/**
 * Created by j.boeser on 24-2-2017.
 */

public class ListItem implements Serializable {

    private String title;
    private String season;

    public ListItem (String title, String season) {
        this.title = title;
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public String getSeason() { return "Seizoen " + season; }
}
