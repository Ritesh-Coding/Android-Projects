package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String []arr={"ITEM1","ITEM2","ITEM3","ITEM4","ITEM5","ITEM6","ITEM7","ITEM8","ITEM9","ITEM10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //here layout manager work is to set the items in which layout here we have linear layout
        CustomAdapter c=new CustomAdapter(arr);
        recyclerView.setAdapter(c);
    }
}