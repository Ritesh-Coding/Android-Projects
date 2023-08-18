package com.example.stop_watch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textView;
//    int seconds=0;
    int milliseconds=0;
    boolean isRunning; //default it is false   we have made this so that our timer shows the deafult 00:00:00 till we not click the start button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.timer);
        startTimer();
    }
    public void onclickStart(View view)
    {
        isRunning=true;
    }
    public void onclickStop(View view)
    {
        isRunning=false;
    }
    public void onclickReset(View view)
    {
        isRunning=false;   //here we stop the time and reset the seconds to 0
//        seconds=0;
        milliseconds=0;
    }
    public void startTimer()
    {
        Handler handler = new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
//                int s=seconds % 60;   //simple logic as 1 to 60  here we made the logic to get the seconds minutes and hours only
                                        //so just changed the postdelayed to 1000 and set string format
//                int m = seconds/60;
//                int h= m/60;

                int ms=milliseconds%100;
                int secs=(milliseconds/60)%60;
                int mins=milliseconds/3600;
                if (isRunning)
                {
//                    seconds++;   //update the seconds
                    milliseconds++;
                }

                String formatString = String.format(Locale.getDefault(),"%02d:%02d:%02d",mins,secs,ms);
                textView.setText(formatString);

                handler.postDelayed(this,10);//repeat the steps after every 1 sec
            }
        };
        handler.post(runnable);    //we made this runnable now
    }


}