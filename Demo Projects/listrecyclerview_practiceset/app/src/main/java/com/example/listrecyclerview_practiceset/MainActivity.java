package com.example.listrecyclerview_practiceset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Contact o1=new Contact(1,"89465656","Ritesh");
    Contact o2=new Contact(1,"89465656","Ritesh1");
    Contact o3=new Contact(1,"89465656","Ritesh2");
    Contact o4=new Contact(1,"89465656","Ritesh3");
    Contact o5=new Contact(1,"89465656","Ritesh4");
    Contact o6=new Contact(1,"89465656","Ritesh5");
    Contact o7=new Contact(1,"89465656","Ritesh6");
    Contact o8=new Contact(1,"89465656","Ritesh7");
    Contact o9=new Contact(1,"89465656","Ritesh8");
    Contact o10=new Contact(1,"89465656","Ritesh9");

    Contact [] contacts={o1,o2,o3,o4,o5,o6,o7,o8,o9};
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
    }
}