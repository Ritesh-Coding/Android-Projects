package com.example.dbdemocodewithharry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dbdemocodewithharry.data.MyDBHandler;
import com.example.dbdemocodewithharry.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDBHandler db = new MyDBHandler(MainActivity.this);

        // Creating a contact object for the db
        Contact harry = new Contact();
        harry.setPhoneNumber("9090909090");
        harry.setName("Harry");

        // Adding a contact to the db
        db.addContact(harry);

        // Creating a contact object for the db
        Contact larry = new Contact();
        larry.setPhoneNumber("9090459090");
        larry.setName("Larry");

        // Adding a contact to the db
        db.addContact(larry);

        // Creating a contact object for the db
        Contact tehri = new Contact();
        tehri.setPhoneNumber("9090675409");
        tehri.setName("Tehri");

        // Adding a contact to the db
        db.addContact(tehri);
        Log.d("dbharry", "Id for tehri and larry are successfully added to the db");

        tehri.setId(3);
        tehri.setName("Changed Tehri");
        tehri.setPhoneNumber("0000000000");

        int affectedRows = db.updateContact(tehri); //function cAll to update


        Log.d("dbharry", "No of affected rows are: " + affectedRows);

//        db.deletecontactById(1);
//        db.deletecontactById(2);

        //creating an arraylist for creating a contact
        ArrayList<String> contactstoshow=new ArrayList<>();
        listview=findViewById(R.id.listview);


        // Get all contacts
        List<Contact> allContacts = db.getAllContacts();
        for(Contact contact: allContacts){
            Log.d("dbharry", "\nId: " + contact.getId() + "\n" +
                    "Name: " + contact.getName() + "\n"+
                    "Phone Number: " + contact.getPhoneNumber() + "\n" );
            contactstoshow.add(contact.getName()+" ("+contact.getPhoneNumber()+") ");

        }
        //here we have made an adapter to show the contacts
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contactstoshow);
        listview.setAdapter(arrayAdapter);
//        Log.d("dbharry","Bro you have "+db.getCount()+" contacts in your database");


    }
}

