package com.vladislavtachev.cscb763;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vladislav Tachev on 7/2/2015.
 * All rights reserved.
 */

public class SightCursAdapter extends CursorAdapter {

    static Button delBtn;

    public SightCursAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView tvLoc = (TextView) view.findViewById(R.id.locFld);
        TextView tvTime = (TextView) view.findViewById(R.id.timeFld);
        TextView tvBName = (TextView) view.findViewById(R.id.bNameFld);
        TextView tvBCount = (TextView) view.findViewById(R.id.bCountFld);
        delBtn = (Button) view.findViewById(R.id.delBtn);
        String loc = cursor.getString(cursor.getColumnIndexOrThrow("_loc"));
        long time = cursor.getInt(cursor.getColumnIndexOrThrow("_time"));
        String bName = cursor.getString(cursor.getColumnIndexOrThrow("_bname"));
        int bCount = cursor.getInt((cursor.getColumnIndexOrThrow("_bcount")));
        long id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        delBtn.setTag(id);
        tvLoc.setText(loc);
        tvTime.setText(String.valueOf(new java.util.Date(time*1000)));
        tvBName.setText(bName);
        tvBCount.setText(String.valueOf(bCount));
    }

    public void deleteItem(long itemID, Context context){
        SightingsDataSource dataSource = new SightingsDataSource(context);
        dataSource.open();
        dataSource.deleteEntry(itemID);
        Toast msg = Toast.makeText(context.getApplicationContext(),"Sighting successfully deleted.", Toast.LENGTH_SHORT);
        msg.show();
    }

    @Override
    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
    }
}
