package com.vladislavtachev.cscb763;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
}
