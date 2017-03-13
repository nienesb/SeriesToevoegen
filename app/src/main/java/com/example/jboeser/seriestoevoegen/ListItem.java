package com.example.jboeser.seriestoevoegen;

import java.io.Serializable;

/**
 * Created by j.boeser on 24-2-2017.
 */

public class ListItem implements Serializable {

    private long id;
    private String title;
    private String season;

    public ListItem() {
        this.id = id;
        this.title = this.title;
        this.season = this.season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
