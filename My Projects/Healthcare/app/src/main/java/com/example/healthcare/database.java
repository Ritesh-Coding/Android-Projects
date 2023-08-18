package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {//for creating database
        String qry1 = "create table users(Username text,email text,password text)";
        db.execSQL(qry1);

        String qry2 = "create table cart(Username text,product text,price float,otype text)";//it will work for both buy medicine in Home page and
        //add to cart in the Lab test in home page
        db.execSQL(qry2);

        String qry3 = "create table orderplace(Username text,fullname text,address text,contactno text,pincode Integer,date text,time text,amount float,otype text)";
        db.execSQL(qry3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //for updateing table

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db1 = getWritableDatabase();
        db1.insert("users", null, cv);
        db1.close();
    }

    public int login(String username, String password) {
        int result = 0;  //initially there is no record while the user clicks on the login button
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from users where username=? and password=?", str);  //here there are 2 question mark and we want to add in these place the 2 string so we
        //have cretead the 2 strings above
        if (c.moveToFirst())  //it wil move into the table if it has found some records
        {
            result = 1;
        }

        return result;
    }

    public void addtocart(String username, String product, float price, String otype)  //otype means the order of lab or medicine
    {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db1 = getWritableDatabase();
        db1.insert("cart", null, cv);
        db1.close();
    }

    public int checkCart(String username, String product)  //check whether the product is in cart already
    {
        int result1 = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from cart where username=? and product=?", str);  //here there are 2 question mark and we want to add in these place the 2 string so we
        //have cretead the 2 strings above
        if (c.moveToFirst())  //it wil move into the table if it has found some records
        {
            result1 = 1;
        }
        db.close();
        return result1;

    }

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and otype=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype)//here we return array
    {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("Select * from cart where username=? and otype=?", str);
        if (c.moveToFirst())                                      //it wil move into the table if it has found some records
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);

            } while (c.moveToNext());
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contactno, Integer pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contactno);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    public ArrayList getOrderDetails(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username=?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6) + "$" + c.getString(7) + "$" + c.getString(8));


            } while (c.moveToNext());
        }
        db.close();
        return arr;

    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result = 0;

        String str[] = new String[5];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = date;
        str[4] = time;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE username=? AND fullname=? AND address=? AND date=? AND time=?", str);

        if (c.moveToFirst()) {
            result = 1;
        }

        c.close();
        db.close();
        return result;
    }


}








