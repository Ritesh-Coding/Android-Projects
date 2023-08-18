package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnAddtoCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textviewBUYMEDICINECart);
        tvTotalCost = findViewById(R.id.textViewBUYMEDICINRTotalCost);
        edDetails = findViewById(R.id.edittextBMDMultiline);
        btnBack = findViewById(R.id.Button_BM_cart_Back);
        btnAddtoCart = findViewById(R.id.buttonBM_Cart_Checkout);
        edDetails.setKeyListener(null);//now the edittext cannot be modified


        Intent intent = getIntent();

        tvPackageName.setText(intent.getStringExtra("text1")); //our product name

        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Price : " + intent.getStringExtra("text3")); //our product price

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());  //typecast in float


                database db = new database(getApplicationContext(), "healthcare", null, 1);
                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product already added", Toast.LENGTH_SHORT).show();
                } else {

                    db.addtocart(username, product, price, "lab");
                    Toast.makeText(getApplicationContext(), "Record Inserted to the cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
        finish();
    }
}