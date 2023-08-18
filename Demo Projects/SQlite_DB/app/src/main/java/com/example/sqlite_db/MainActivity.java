package com.example.sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            DB_handler handler = new DB_handler(this,"empdb",null,1);
//            handler.addEmployee(new Employee(10,"Ritesh",33.3));
            handler.getEmployee(10);
            handler.close();

    }
}