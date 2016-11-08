package com.example.tammy.test.DataRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by YNation on 11/8/2016.
 */

public class ClassDatabaseHelper extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "classes.db";
    private static final String TABLE_NAME = "class";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_START_TIME = null;
    private static final String COLUMN_END_TIME = null;
    private static final String COLUMN_LONGITUDE = null;
    private static final String COLUMN_LATITUDE = null;
    private static final String COLUMN_RADIUS = null;

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table class (id integer primary key not null , " +
            "name text not null , start_time date not null , end_time date not null , longitude decimal not null , latitude decimal not null, radius decimal not null);";


    public ClassDatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    public void insertClass(ClassInformation _class)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);

        values.put(COLUMN_END_TIME, _class.getEnd_time().toString());
        values.put(COLUMN_START_TIME, _class.getStart_time().toString());
        values.put(COLUMN_NAME, _class.getName());
        values.put(COLUMN_LATITUDE, _class.getLatitude());
        values.put(COLUMN_LONGITUDE, _class.getLongitude());
        values.put(COLUMN_RADIUS,  _class.getRadius());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
