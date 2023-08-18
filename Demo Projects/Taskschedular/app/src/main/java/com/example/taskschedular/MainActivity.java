package com.example.taskschedular;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int number =1;
    int count=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(MainActivity.this, "The app will show this message for the 35sec" + count, Toast.LENGTH_SHORT).show();
                count--;
            }


            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Khatam ho gya ", Toast.LENGTH_SHORT).show();
            }
        }.start();

        //Task schedular using Handler and Runnable
        //With the help of Handler and Runnable we can easily set the task
//        final Handler handler=new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                //code to execute
//                number++;
//                Toast.makeText(MainActivity.this, "This is no :- "+number, Toast.LENGTH_SHORT).show();
//                handler.postDelayed(this,1000);
//            }
//        };
//        handler.post(run);
    }
}