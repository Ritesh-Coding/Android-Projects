package com.example.shared_resources;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     EditText editText;
     TextView textView;
     Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);

        SharedPreferences sP =getSharedPreferences("MyPref",MODE_PRIVATE);
        String editVal = sP.getString("name","No value as right");
        textView.setText(editVal);
        //shared preferences are used to store the data of the highest score in a game
        //Here we have shared preference means we can store the value in the top of the disk storage in the form of the key value form
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  val =editText.getText().toString();
                SharedPreferences sP=getSharedPreferences("MyPref",MODE_PRIVATE);
                SharedPreferences.Editor ed =sP.edit();
                ed.putString("name",val);
                ed.apply();
                textView.setText(val);
            }
        });


    }
}