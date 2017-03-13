package com.example.jboeser.seriestoevoegen;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by j.boeser on 1-3-2017.
 */

public class SeriesCursorWrapper extends CursorWrapper {

    public SeriesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ListItem getSeries() {
        long id = getLong(getColumnIndex(SeriesDBSchema.SeriesTable.Colums._id));
        String title = getString(getColumnIndex(SeriesDBSchema.SeriesTable.Colums.TITLE));
        String season = getString(getColumnIndex(SeriesDBSchema.SeriesTable.Colums.SEASON));

        ListItem series = new ListItem();
        series.setId(id);
        series.setTitle(title);
        series.setSeason(season);

        return series;
    }
}
