package com.example.sqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DB_handler extends SQLiteOpenHelper { //SQLiteOpenHandler comes under android sqlite class
    public DB_handler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name,factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create ="CREATE TABLE myemployee(sno INTEGER PRIMARY KEY,name TEXT,increment TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = String.valueOf("Drop TABLE IF EXIXTS");
        db.execSQL(drop, new String[]{"myemployee"});
        onCreate(db);
    }


        public void addEmployee(Employee emp) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();//instance to add values
            values.put("name", emp.getName()); //key value pairs
            values.put("increment", emp.getIncrement());//key value pairs
            long k = db.insert("myemployee", null, values);  //goto documentation
            Log.d("mytag", Long.toString(k));
            db.close();
        }

        public void getEmployee(int sno)
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("myemployee",new String[]{"sno","name","increment"},"sno=?",new String[]{String.valueOf("sno")},null,null,null);

            if(cursor != null && cursor.moveToFirst()){
                Log.d("mytag",cursor.getString(1));
                Log.d("mytag",cursor.getString(2));
                Log.d("mytag",cursor.getString(3));

            }
            else
            {
                Log.d("mytag","Some error occured");
            }

    }

}
