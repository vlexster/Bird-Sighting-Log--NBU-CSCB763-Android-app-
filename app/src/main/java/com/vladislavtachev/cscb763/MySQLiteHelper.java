package com.vladislavtachev.cscb763;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

/**
 * Created by Vladislav Tachev on 6/29/2015.
 * All rights reserved.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    public final static String DB_NAME = "sightings.db";
    public final static int DB_VER = 1;
    public final static String TABLE_SIGHTINGS = "sightings";
    public final static String COL_ID = "_id";
    public final static String COL_LOCATION = "_loc";
    public final static String COL_TIME = "_time";
    public final static String COL_BNAME = "_bname";
    public final static String COL_BCOUNT = "_bcount";

    private final static String DB_CREATE = "create table if not exists "
            + TABLE_SIGHTINGS + "("
            + COL_ID + " integer primary key autoincrement, "
            + COL_LOCATION + " text not null, "
            + COL_TIME + " integer not null, "
            + COL_BNAME + " text not null, "
            + COL_BCOUNT + " integer not null);";

    public MySQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVer, int newVer){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGHTINGS);
        onCreate(database);
    }

}
