package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datebutton;
    private Button btnCheckout;
    private Button timebutton;
    private Button btnback;
    private String[][] packages = {};
    float totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);
        datebutton = findViewById(R.id.button_BM_CartDate);
        timebutton = findViewById(R.id.buttoncart_timepicker);
        btnCheckout = findViewById(R.id.buttonBM_Cart_Checkout);
        btnback = findViewById(R.id.Button_BM_cart_Back);
        tvTotal = findViewById(R.id.textViewBUYMEDICINRTotalCost);
        lst = findViewById(R.id.listViewBMCart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        database db = new database(getApplicationContext(), "healthcare", null, 1);


        ArrayList dbData = db.getCartData(username, "lab");//****************************************************************

        packages = new String[dbData.size()][];//***************************************************************************************
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }


        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));    //Not understood
            packages[i][0] = strData[0];
            packages[i][4] = "Cost :" + strData[1] + "/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);

            Log.d("packagesData >>", packages[i][0].toString());
            Log.d("packagesData >>", packages[i][4].toString());

        } //**************************************************************************************************************************


        tvTotal.setText("Total Cost :" + totalAmount);
        list = new ArrayList();
        for (int i = 0; i < packages.length; i++)  //here we map the text
        {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]); //accoring to the column we add the data into the list
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);

        }
        //from list we fetch the data
        sa = new SimpleAdapter(this, list,                  //here we map the button
                R.layout.multil_ines,  //multilines is the design
                new String[]{"line1", "line2", "line3", "line4", "line5"},  //from line a,b...
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e} //to   these id
        );


        lst.setAdapter(sa);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(CartLabActivity.this, LabTestBookActivity.class);
                it.putExtra("price", totalAmount);
                it.putExtra("date", datebutton.getText());
                it.putExtra("time", timebutton.getText());
                startActivity(it);
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartLabActivity.this, LabTestActivity.class));
            }
        });

        //datepicker
        initDatePicker();
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //timepicker
        initTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                datebutton.setText(i2 + "/" + i1 + "/" + i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timebutton.setText(hourOfDay + ":" + minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CartLabActivity.this, LabTestActivity.class));
        finish();

    }


}