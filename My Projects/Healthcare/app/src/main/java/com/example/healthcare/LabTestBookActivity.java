package com.example.healthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnbooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        edname = findViewById(R.id.edittext_name);
        edaddress = findViewById(R.id.edittextBMBAddress);
        edcontact = findViewById(R.id.edittext_BMB_Contactno);
        edpincode = findViewById(R.id.edittext_BMB_pincode);
        btnbooking = findViewById(R.id.Button_BMB_Booking1);
//
        Intent intent = getIntent();
        Float price = intent.getFloatExtra("price", 0f);


        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");




        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edname.getText().toString().isEmpty() && edpincode.getText().toString().isEmpty() && edcontact.getText().toString().isEmpty() && edaddress.getText().toString().isEmpty()) {
                    Toast.makeText(LabTestBookActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    return;
                }

                 if (edname.getText().toString().isEmpty()) {
                    Toast.makeText(LabTestBookActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edaddress.getText().toString().isEmpty()) {
                    Toast.makeText(LabTestBookActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edcontact.getText().toString().isEmpty()) {
                    Toast.makeText(LabTestBookActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edpincode.getText().toString().isEmpty()) {
                    Toast.makeText(LabTestBookActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (containsOnlyNumbers(edname.getText().toString())) {
                    showToast("Integers are not allowed in username");
                    return;
                }
                if(containsOnlyStrings(edpincode.getText().toString()))
                {
                    showToast("Characters are not allowed in the pin code");
                    return;
                }
                if(containsOnlyStrings(edcontact.getText().toString()))
                {
                    showToast("Characters are not allowed in the Contact No");
                    return;
                }

                else{
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedPreferences.getString("username", "").toString();

                    Integer pincode = 0;

                    if (!edpincode.getText().toString().isEmpty()) {
                        pincode = Integer.parseInt(edpincode.getText().toString());
                    }
                    database db = new database(getApplicationContext(), "healthcare", null, 1);
                    db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), pincode, date.toString(), time.toString(), price, "lab");

                    db.removeCart(username, "lab");

                    Toast.makeText(LabTestBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestBookActivity.this, OrderDetailsActivity.class));
                }
            }
        });


    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Helper method to check if a string contains only numbers
    private boolean containsOnlyNumbers(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean containsOnlyStrings(String str)
    {
        try{
            Integer.parseInt(str);
            return false;
        }
        catch (NumberFormatException e)
        {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(LabTestBookActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}

    
