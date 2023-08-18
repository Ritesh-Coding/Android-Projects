package com.example.dbdemocodewithharry.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.dbdemocodewithharry.model.Contact;
import com.example.dbdemocodewithharry.params.params;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    public MyDBHandler(Context context) {
        super(context, params.DB_NAME, null, params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + params.TABLE_NAME + "("
                + params.KEY_ID + " INTEGER PRIMARY KEY," + params.KEY_NAME
                + " TEXT, " + params.KEY_PHONE + " TEXT" + ")";
        Log.d("dbharry", "Query being run is : "+ create);
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact){  //to write it into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(params.KEY_NAME, contact.getName());
        values.put(params.KEY_PHONE, contact.getPhoneNumber());


        db.insert(params.TABLE_NAME, null, values);
        Log.d("dbharry", "Successfully inserted");
        db.close();


    }

    public List<Contact> getAllContacts(){  //to read from the database
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){  //cursor shows the data 1 row after the other and so on
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(params.KEY_NAME, contact.getName()); //key name store the name of the contacts from the user
        values.put(params.KEY_PHONE, contact.getPhoneNumber());

        //Lets update now
        return db.update(params.TABLE_NAME, values, params.KEY_ID + "=?",  //+? shows that the query Id is equal to that of the ID given by the user in the string format
                new String[]{String.valueOf(contact.getId())});
   }
   public void deletecontactById(int id)  //to delete the contact by ID
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(params.TABLE_NAME,params.KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void deletecontact(Contact contact) //to delete the contact by name
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(params.TABLE_NAME,params.KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getCount(){
        String query="SELECT * FROM "+ params.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }


}

