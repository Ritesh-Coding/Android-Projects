package com.example.firstmultiscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.textView);
                                            //here we have the best example
        Intent intent = getIntent();//get the name from the mainactivity
        String name=intent.getStringExtra(MainActivity.EXTRA_NAME);//here we import from the main activity

        textView.setText("Your name is: "+ name);
    }
}