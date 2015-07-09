package com.vladislavtachev.cscb763;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav Tachev on 6/29/2015.
 * All rights reserved.
 */

public class MainActivity extends ListActivity {

    public static final List<Sighting> Sightings = new ArrayList<Sighting>();
    private SightCursAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SightingsDataSource datasource = new SightingsDataSource(this);
        datasource.open();
        Cursor cursor = datasource.getAllSightingsAsCursor();
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setLongClickable(true);
        adapter = new SightCursAdapter(this, cursor);
        listView.setAdapter(adapter);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAct = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(addAct);
//                setContentView(R.layout.activity_add);
            }
        });
    }

    public void deletebuttonShow(View view) {
       final View delbutton =  view.findViewById(R.id.delBtn);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (delbutton.getVisibility()== View.INVISIBLE) {
                    delbutton.setVisibility(View.VISIBLE);
                } else delbutton.setVisibility(View.INVISIBLE);
                return true;
            }
        });
    }

    public void deleteEntry(View view) {
      adapter.deleteItem((Long) view.getTag(), this);
        SightingsDataSource datasource = new SightingsDataSource(this);
        datasource.open();
        Cursor cursor = datasource.getAllSightingsAsCursor();
        adapter.swapCursor(cursor);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void onListItemClick(ListView l, View v, int pos, long id){
//        SightingsDataSource datasource = new SightingsDataSource(this);
//        datasource.open();
//        Cursor cursor = datasource.getAllSightingsAsCursor();
//        ListView listView = (ListView) findViewById(android.R.id.list);
//        SightCursAdapter adapter = new SightCursAdapter(this, cursor);
//        listView.setAdapter(adapter);
//        SightingsDataSource datasource = new SightingsDataSource(this);
//        datasource.open();
//
//        Sighting item = (Sighting) getListAdapter().getItem(pos);
//        long idToDelete = item.getId();
//        datasource.deleteEntry(idToDelete);
//        datasource.close();
//    }



}
