package com.vladislavtachev.cscb763;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Vladislav Tachev on 6/29/2015.
 * All rights reserved.
 */

public class AddActivity extends ListActivity {

    private SightingsDataSource datasource;

    private static EditText locFld = null;
    private static EditText bNameFld = null;
    private static EditText bCountFld = null;
    private static Button addSightBtn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        addSightBtn = (Button) findViewById(R.id.addSightBtn);
        addSightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSight(v);
            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_add, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addSight(View view){
        datasource = new SightingsDataSource(this);
        datasource.open();
        locFld = (EditText) findViewById(R.id.locFld);
        bNameFld = (EditText) findViewById(R.id.bNameFld);
        bCountFld = (EditText) findViewById(R.id.bCountFld);
                datasource.addSighting(locFld.getText().toString(),
                        System.currentTimeMillis() / 1000L,
                        bNameFld.getText().toString(),
                        Integer.parseInt(bCountFld.getText().toString()));
        datasource.close();
        finish();
        Intent addAct = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(addAct);

    }
}
