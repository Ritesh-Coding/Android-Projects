package com.example.codewithharry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {  //inheritance means that all the activities of the appcompact will be in the mainactivity

    private Button button;
    private TextView textView;
    private EditText editText;




    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            button = findViewById(R.id.button);       //button textView and editText are java objects we assigned in reference as xml
            textView = findViewById(R.id.textView2);
            editText = findViewById(R.id.editText);

       /* button.setOnClickListener(new View.OnClickListener() {  //onClickListener is an interface in which some of the methods are given to which they have to implement
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Hi click listener worked", Toast.LENGTH_SHORT).show();
                String s=editText.getText().toString();//we have converted the text digits into the string
                int kg=Integer.parseInt(s);
                double pound=2.205*kg;
                textView.setText("The corresponding value in pound is "+ pound);
            }
        });
        */



            //here in upward we have make the app with the help of an onClickListner and now we make the app with the help of the onclick attribute
        }
        public void calculate(View view)
        {
            String s=editText.getText().toString();//we have converted the text digits into the string
            int kg=Integer.parseInt(s);
            double pound=2.205*kg;
            textView.setText("The corresponding value in pound is "+ pound);
        }
}