package com.example.jboeser.seriestoevoegen;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by j.boeser on 1-3-2017.
 */

public class SeriesAdapter extends CursorAdapter {

    public SeriesAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.row_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        SeriesCursorWrapper cursorWrapper = (SeriesCursorWrapper) cursor;
        ListItem serie = cursorWrapper.getSeries();

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(serie.getTitle());

        TextView season = (TextView) view.findViewById(R.id.season);
        season.setText("Seizoen " + serie.getSeason());
    }
}
