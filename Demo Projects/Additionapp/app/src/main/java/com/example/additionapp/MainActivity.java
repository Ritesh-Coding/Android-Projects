//mipmap is used to add icons to the app
//drawable is used to draw an object
package com.example.additionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        EditText value1,value2;
        TextView total;
        Button btn;
        int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1=findViewById(R.id.editTextNumber1);
        value2=findViewById(R.id.editTextNumber2);   //reference . id
        btn=findViewById(R.id.button1);
        total=findViewById(R.id.text);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String num1=value1.getText().toString();
                String num2=value2.getText().toString();

                result=Integer.parseInt(num1)+Integer.parseInt(num2);
                total.setText("Addition:-" +Integer.toString(result));
            }

        });

    }
}