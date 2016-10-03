package com.example.tammy.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tammy on 10/2/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "firstname";
    private static final String COLUMN_LAST_NAME = "lastname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table users (id integer primary key not null , " +
            "firstname text not null , lastname text not null , email text not null , username text not null , password text not null);";


    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    public void insertUser(UserInformation user)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);

        values.put(COLUMN_FIRST_NAME, user.getFirstname());
        values.put(COLUMN_LAST_NAME, user.getLastname());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public String searchPass(String email){
        db = this.getReadableDatabase();
        String query = "select email, password from " +TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(email))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    public boolean alreadyExist(String email){
        db = this.getReadableDatabase();
        String query = "select email from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        boolean exists = false;
        String temp;

        if(cursor.moveToFirst()){
            do{
                temp = cursor.getString(0);

                if(temp.equals(email))
                {
                    exists = true;
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return exists;
    }

    public String getFirstName(String email){
        db = this.getReadableDatabase();
        String query = "select email, firstname from " +TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(email))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
