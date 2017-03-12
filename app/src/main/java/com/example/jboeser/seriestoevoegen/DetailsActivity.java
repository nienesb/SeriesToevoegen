package com.example.jboeser.seriestoevoegen;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static com.example.jboeser.seriestoevoegen.SeriesDBSchema.SeriesTable.Colums._id;

public class DetailsActivity extends AppCompatActivity {

    private ListView mListView;
    private SeriesDataSource mDatasource;
    private Cursor mCursor;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mDatasource = new SeriesDataSource(this);
        id = getIntent().getLongExtra(_id, 0);

        TextView title  = (TextView) findViewById(R.id.activity_details_title);
        TextView season = (TextView) findViewById(R.id.activity_details_season);


        //Get the object from the intent
        ListItem clickedItem = mDatasource.getSeriesById(id);
        if(clickedItem != null) {
            //Set the values from the object to the views
            title.setText(clickedItem.getTitle());
            season.setText(clickedItem.getSeason());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }
}
