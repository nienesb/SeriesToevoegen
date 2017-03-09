package com.example.jboeser.seriestoevoegen;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<ListItem> mItems;
    private ListView mListView;
    private CursorAdapter mAdapter;
    private SeriesDataSource mDatasource;
    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.list_view);
        mItems = new ArrayList<ListItem>();
        mDatasource = new SeriesDataSource(this);

        registerForContextMenu(mListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                ListItem clickedItem = (ListItem) parent.getItemAtPosition(position);
                intent.putExtra("clickedItem", clickedItem);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewItemActivity.class);
//                System.out.println("hallo");return;
                startActivity(intent);
            }
        });

        /*mListView.setOnItemLongClickListener(new AdapterView.onItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mDatasource.deleteSerie(id);
                updateUi();
                return true;
            }
        });*/

        updateUi();
    }

    private void updateUi() {
        mCursor = mDatasource.getAllSeries();
        if (mAdapter == null) {
            mAdapter = new SeriesAdapter(this, mCursor);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.changeCursor(mCursor);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {
            //Check if the request code is correct
            if (requestCode == 1234) {
                //Create a list item from the values
                ListItem item = (ListItem) data.getSerializableExtra("newItem");
                //Add the new item to the mAdapter;
                mItems.add(item);
                //Have the mAdapter update
                updateUi();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.context_menu_delete_item) {
            //Remove the item from the list
            mItems.remove(itemInfo.position);
            //Update the adapter to reflect the list change
            updateUi();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    // deleting all items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_item) {
            mItems.clear();
            updateUi();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
