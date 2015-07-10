package com.vladislavtachev.cscb763;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
