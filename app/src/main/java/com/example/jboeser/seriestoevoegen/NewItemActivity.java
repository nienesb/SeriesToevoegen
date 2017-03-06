package com.example.jboeser.seriestoevoegen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewItemActivity extends AppCompatActivity {

    private SeriesDataSource mDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        mDatasource = new SeriesDataSource(this);

        final EditText mTitleEditText  = (EditText) findViewById(R.id.title_input);
        final EditText mSeasonEditText = (EditText) findViewById(R.id.season_input);

        Button button = (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the title and descriptions have text
                if (!TextUtils.isEmpty(mTitleEditText.getText()) && !TextUtils.isEmpty(mSeasonEditText.getText())) {
                    ListItem serie = new ListItem();
                    serie.setTitle(mTitleEditText.getText().toString());
                    serie.setSeason(mSeasonEditText.getText().toString());
                    mDatasource.addSeries(serie);
                    Intent intent = new Intent(NewItemActivity.this, ListActivity.class);
                    startActivity(intent);
                } else {
                    //Show a message to the user
                    Toast.makeText(NewItemActivity.this, "Je hebt niet alles ingevuld!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
