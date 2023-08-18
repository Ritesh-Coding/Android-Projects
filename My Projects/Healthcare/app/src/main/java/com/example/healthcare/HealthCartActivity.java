package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class HealthCartActivity extends AppCompatActivity {
    private String[][] health_details =
            {
                    {"Walking Daily", "", "", "", "Click More Details"},
                    {"Home Care to covid 19", "", "", "", "Click More Details"},
                    {"Stop Smoking", "", "", "", "Click More Details"},
                    {"Menstrual Cramps", "", "", "", "Click More Details"},
                    {"Healthy Gut", "", "", "", "Click More Details"}
            };
    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    Button btnBack;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_cart);
        btnBack = findViewById(R.id.Button_Back_HA);
        lst = findViewById(R.id.listviewHA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthCartActivity.this, HomeActivity.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < health_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);

            list.add(item);
        }
        //from list we fetch the data
        sa = new SimpleAdapter(this, list,                  //here we map the button
                R.layout.multil_ines,  //multilines is the design
                new String[]{"line1", "line2", "line3", "line4", "line5"},  //from line a,b...
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}); //to   these id

        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(HealthCartActivity.this, HealthArticlesDetailsActivity.class);
                it.putExtra("text1", health_details[position][0]);
                it.putExtra("text2", images[position]);

                startActivity(it);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(HealthCartActivity.this, HomeActivity.class));
        finish();

    }
}