package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String arr[]={"This is","Item","Item2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        //using builtin adapter
//        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        //listView.setAdapter(ad);

//       using custom adapter
         riteshadapter ad=new riteshadapter(this,R.layout.myriteshlayout,arr);
        listView.setAdapter(ad);  //now visit riteshadapter.java line34


        //Applied listener on click on builtin adapter listview
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView,View view,int i ,long l)
//            {
//                Toast.makeText(MainActivity.this, "You clicked on "+i, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}