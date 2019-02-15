package com.example.mvc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final class Model {

    private static final String DB_NAME="taska_db";
    private static final String TABLE_NAME = "task";
    private static final int VERSON = 1;

    private static final String DB_CREATION_QUERY = "CREATE TABLE "+TABLE_NAME +"(id integer primary key autoincrement,title text not null);";

    private SQLiteDatabase database;

    private SQLiteOpenHelper helper;

    public Model(Context context){

        this.helper =  new SQLiteOpenHelper(context,DB_NAME,null,VERSON) {
            @Override
            public void onCreate(SQLiteDatabase db) {

                db.execSQL(DB_CREATION_QUERY);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                this.onCreate(db);
            }
        };
        this.database = helper.getWritableDatabase();
    }

    public void addTask(ContentValues contentValues){
        database.insert(TABLE_NAME,null,contentValues);
    }
    public void deletTask(String feild_params ){
        database.delete(TABLE_NAME,feild_params,null);

    }
    public Cursor loadTask(){

        Cursor cursor=database.query(TABLE_NAME,
                new String[]{"title"},
                null,null,null,null,null);
        return cursor;
    }
}
