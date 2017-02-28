package com.example.jboeser.seriestoevoegen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        final EditText mTitleEditText  = (EditText) findViewById(R.id.title_input);
        final EditText mSeasonEditText = (EditText) findViewById(R.id.season_input);

        Button button = (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the title and descriptions have text
                if (!TextUtils.isEmpty(mTitleEditText.getText()) && !TextUtils.isEmpty(mSeasonEditText.getText())) {
                    String title = mTitleEditText.getText().toString();
                    String season = mSeasonEditText.getText().toString();
                    ListItem newItem = new ListItem(title, season);
                    //Create a new intent with the entered data
                    Intent data = new Intent();
                    data.putExtra("newItem", newItem);
                    //Send the result back to the activity
                    setResult(Activity.RESULT_OK, data);
                    finish();
                } else {
                    //Show a message to the user
                    Toast.makeText(NewItemActivity.this, "Je hebt niet alles ingevuld!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
