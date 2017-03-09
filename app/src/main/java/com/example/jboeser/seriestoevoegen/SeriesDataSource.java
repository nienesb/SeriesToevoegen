package com.example.jboeser.seriestoevoegen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by j.boeser on 1-3-2017.
 */

public class SeriesDataSource {

    private SQLiteDatabase mDatabase;

    public SeriesDataSource(Context context) {
        mDatabase = new SeriesBaseHelper(context).getWritableDatabase();
    }

    private static ContentValues getContentValues(ListItem series) {
        ContentValues values = new ContentValues();

        values.put(SeriesDBSchema.SeriesTable.Colums.TITLE, series.getTitle());
        values.put(SeriesDBSchema.SeriesTable.Colums.SEASON, series.getSeason());

        return values;
    }

    public void addSeries(ListItem series) {
        ContentValues values = getContentValues(series);

        mDatabase.insert(SeriesDBSchema.SeriesTable.NAME, null, values);
    }

    public void updateSeries(ListItem series) {
        String idString = Long.toString(series.getId());
        ContentValues values = getContentValues(series);

        mDatabase.update(SeriesDBSchema.SeriesTable.NAME, values,
                SeriesDBSchema.SeriesTable.Colums._id + "=?",
                new String[]{idString});
    }

    private SeriesCursorWrapper querySeries(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                SeriesDBSchema.SeriesTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new SeriesCursorWrapper(cursor);
    }

    public Cursor getAllSeries() {
        return querySeries(null, null);
    }

    public ListItem getSeriesById(long id) {
        SeriesCursorWrapper cursor = querySeries(SeriesDBSchema.SeriesTable.Colums._id + "=?",
                new String[]{Long.toString(id)});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getSeries();
        } finally {
            cursor.close();
        }
    }

    public void deleteSerie(long id) {
        String idString = Long.toString(id);

        mDatabase.delete(SeriesDBSchema.SeriesTable.NAME,
                SeriesDBSchema.SeriesTable.Colums._id + "=?",
                new String[]{idString});
    }
}
