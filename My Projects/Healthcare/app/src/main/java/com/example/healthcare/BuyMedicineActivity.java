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

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Uprise-03 1000U Capsules","","","","50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsule","","","","350"},
                    {"Vitamin B Complex Capsules","","","","448"},
                    {"Inliife Vitamin E Wheat Germ OiL capsule","","","","448"},
                    {"DOlO 650 target","","","","897"},
                    {"Crocin 650 Advance Tablet","","","","897"},
                    {"Vitamin B Complex Capsules","","","","448"},
                    {"Inliife Vitamin E Wheat Germ OiL capsule","","","","448"},
            };
    private String[] packages_details={
            "Building and keeping the bones and teeth strong\n",
                    "Reducing Stress and mascular pains\n",
                    "Boosting immunity and increasing resistance against infecdtion\n",
                    "Helps in formation of Red Blood Cells\n",
                    "Relives in symptoms of bacteria and other virus that may cause harmful disease.Also leads one in out of danger\n",
                    "Reduces the risk of calcium deficiency,Rickets,and Osteroposrosis\n",
                    "Helps to reduce the iron deficiency\n",
                    "Promotes =mobilty and flexibilty of joints",

    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGotoCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst=findViewById(R.id.listviewBM);
        btnBack=findViewById(R.id.Button_BM_cart_Back);
        btnGotoCart=findViewById(R.id.buttonBM_Cart_Checkout);

        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for (int i=0; i < packages.length; i++)  //here we map the text
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]); //accoring to the column we add the data into the list
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4]+"/-");
            list.add(item);

        }
        //from list we fetch the data
        sa=new SimpleAdapter(this,list,                  //here we map the button
                R.layout.multil_ines,  //multilines is the design
                new String[]{"line1","line2","line3","line4","line5"},  //from line a,b...
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e} //to   these id
        );


        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",packages_details[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);

            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

//        startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
//        finish();

    }
}