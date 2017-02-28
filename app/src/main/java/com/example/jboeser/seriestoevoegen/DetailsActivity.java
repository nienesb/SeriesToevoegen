package com.example.jboeser.seriestoevoegen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView title  = (TextView) findViewById(R.id.activity_details_title);
        TextView season = (TextView) findViewById(R.id.activity_details_season);

        //Get the object from the intent
        ListItem clickedItem = (ListItem) getIntent().getSerializableExtra("clickedItem");
        //Set the values from the object to the views
        title.setText(clickedItem.getTitle());
        season.setText(clickedItem.getSeason());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }
}
