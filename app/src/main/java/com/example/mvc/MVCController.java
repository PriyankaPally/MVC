package com.example.mvc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class MVCController {

    private Model model;
    private List<String> task;

    public MVCController(Context context){

        this.model = new Model(context);
        this.task = new ArrayList<String>();

    }

    public void addTask(String title){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        model.addTask(contentValues);
    }
    public void deleteTask(String title){
        model.deletTask("title"+title);
    }
    public void deleteTask(final long id){
        model.deletTask("id"+id);
    }
    public void deleteAllTasks(){
        model.deletTask(null);
    }
    public List<String> getTask(){
        Cursor cursor = model.loadTask();
        task.clear();
        if (cursor!=null){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                task.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return task;
    }
}
