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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package 1 : Full Body Checkup", "", "", "", "999"},
                    {"Package 2 : Blood Glucose Checkup", "", "", "", "999"},
                    {"Package 3 : Covid 19 Antibody", "", "", "", "999"},
                    {"Package 4 : Thyroid Check", "", "", "", "999"},
                    {"Package 5 : Immunity Check", "", "", "", "999"},
            };
    private String[] package_details = {  //total 5 details are there
            "Blood Glucose Fasting\n" +
                    "Complete Hemogram\n" +
                    "HbA1c\n" + "kidneyFunction Test\n" + "Liver Function Test",
            "Blood Glucose Fasting",
            "Covid 19 Antibody-IgG",
            "Thyroid Profile-Total(T3,T4 & TSH Ultra-Sensitive)",
            "Complete Hemogram\n+" +
                    "Iron Studies\n"


    };
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnGoToCart = findViewById(R.id.buttonBM_Cart_Checkout);
        btnBack = findViewById(R.id.Button_BM_cart_Back);
        listView = findViewById(R.id.listviewBM);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");

            list.add(item);
        }
        //from list we fetch the data
        sa = new SimpleAdapter(this, list,                  //here we map the button
                R.layout.multil_ines,  //multilines is the design
                new String[]{"line1", "line2", "line3", "line4", "line5"},  //from line a,b...
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e} //to   these id
        );


        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                it.putExtra("text1", packages[position][0]);
                it.putExtra("text2", package_details[position]);
                it.putExtra("text3", packages[position][4]);
                startActivity(it);

            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
        finish();

    }
}