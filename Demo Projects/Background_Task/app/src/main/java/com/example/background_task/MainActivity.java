package com.example.background_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
//    In java class inside 1 class is possible
    public class BG extends AsyncTask<String,Void,String>{
                                                                //it takes 3 parameter as 1.String Argument
//                                                            2.Here we do not pass anything to progres hence we wrote Void
//                                                          3.fnally we have to return string   //FOR MORE INFO CONTROL+CLICK

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("ritesh","hii");
//        Toast.makeText(MainActivity.this, "PreExecution Starts", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {  //onPostExecute stores the valye return by doInBackground
        super.onPostExecute(s);
        //Toast.makeText(MainActivity.this, "PostExecution Ends", Toast.LENGTH_SHORT).show();
        Log.d("ritesh","on post execution");
        Log.d("ritesh",s);
    }

    @Override
    protected String doInBackground(String... urls) {
//        Toast.makeText(MainActivity.this, "DoInBackground starts", Toast.LENGTH_SHORT).show();
        Log.d("ritesh","Background starts");
        String result="";
        URL url;
        HttpURLConnection conn;

        try {   //logic to take contents from the website
            url=new URL(urls[0]);
            conn=(HttpURLConnection) url.openConnection();
            InputStream in=conn.getInputStream();
            InputStreamReader reader=new InputStreamReader(in);
            int data= reader.read();
            while(data !=-1)
            {
                char current=(char) data;
                result += current;
                data=reader.read();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }

        return result;

    }

}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BG myTask= new BG();
        myTask.execute("https://www.codewithharry.com/");
    }
    public void ButtonClicked(View view)
    {
        Toast.makeText(this, "Now the button is clicked", Toast.LENGTH_SHORT).show();
    }

}