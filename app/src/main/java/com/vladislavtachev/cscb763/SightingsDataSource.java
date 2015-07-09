package com.vladislavtachev.cscb763;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Vladislav Tachev on 6/29/2015.
 * All rights reserved.
 */
public class SightingsDataSource {

    //DB info
    private SQLiteDatabase database;
//    private MySQLiteHelper dbHelper;
    private SQLiteOpenHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COL_ID, MySQLiteHelper.COL_LOCATION,
            MySQLiteHelper.COL_TIME, MySQLiteHelper.COL_BNAME, MySQLiteHelper.COL_BCOUNT };

    public SightingsDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void addSighting(String loc, long time, String bName, int bCount){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COL_LOCATION, loc);
        values.put(MySQLiteHelper.COL_TIME, time);
        values.put(MySQLiteHelper.COL_BNAME, bName);
        values.put(MySQLiteHelper.COL_BCOUNT, bCount);
        long insertId = database.insert(MySQLiteHelper.TABLE_SIGHTINGS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SIGHTINGS, allColumns, MySQLiteHelper.COL_ID
                + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Sighting newSighting = cursorToSighting(cursor);
        cursor.close();
//        return newSighting;
    }

    private Sighting cursorToSighting(Cursor cursor){
        Sighting sighting = new Sighting();
        sighting.setId(cursor.getLong(0));
        sighting.setLocation(cursor.getString(1));
        sighting.setTime(cursor.getLong(2));
        sighting.setbName(cursor.getString(3));
        sighting.setbCount(cursor.getInt(4));
        return sighting;
    }

    public List<Sighting> getAllSightingsAsList(){
        List<Sighting> sightings = new ArrayList<Sighting>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SIGHTINGS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Sighting sighting = cursorToSighting(cursor);
            sightings.add(sighting);
            cursor.moveToNext();
        }
        cursor.close();
        return sightings;
    }

    public Cursor getAllSightingsAsCursor(){
        Cursor cursor = database.rawQuery("SELECT * FROM sightings", null);
        return cursor;

    }

    public void deleteEntry(long id){
//        database.rawQuery("DELETE FROM sightings WHERE _id = "+id, null);
        int resu = database.delete("sightings", "_id = ?", new String[]{String.valueOf(id)});
        Log.v("Database", "Deleted rows: " + resu);
    }
}
