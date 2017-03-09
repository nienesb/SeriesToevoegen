package com.example.jboeser.seriestoevoegen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by j.boeser on 1-3-2017.
 */

public class SeriesBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "series.db";
    private static final int VERSION = 1;

    public SeriesBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SeriesDBSchema.SeriesTable.NAME + "( " +
                SeriesDBSchema.SeriesTable.Colums._id + " integer primary key autoincrement, " +
                SeriesDBSchema.SeriesTable.Colums.TITLE + ", " +
                SeriesDBSchema.SeriesTable.Colums.SEASON + " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + SeriesDBSchema.SeriesTable.NAME);
        onCreate(db);
    }
}
