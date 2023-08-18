package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Button dateButton,timeButton,btnbook,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv = findViewById(R.id.Appointment_title);
        ed1=findViewById(R.id.edittext_name);
        ed2=findViewById(R.id.edittextBMBAddress);
        ed3=findViewById(R.id.edittext_BMB_pincode);
        ed4=findViewById(R.id.edittext_BMB_Contactno);
        dateButton=findViewById(R.id.button_BM_CartDate);
        timeButton=findViewById(R.id.buttoncart_timepicker);
        btnbook=findViewById(R.id.Button_BMB_Booking1);
        btnback=findViewById(R.id.appointment_back_button);

        ed1.setKeyListener(null); //it means edittext are not modifiable
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it= getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact= it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees:"+fees+"/-");

        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //timepicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointment.this,FindDoctorActivity.class));
            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                database db=new database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
               Log.d("debug", "onClick: there is an error ");
                if(db.checkAppointmentExists(username,fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1)
               {
                   Toast.makeText(BookAppointment.this, "Appointment Already Booked", Toast.LENGTH_SHORT).show();
               }
               else
               {
//                   db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",price,"medicine");
                   db.addOrder(username,fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(), Float.parseFloat(fees),"appointment");
                   Toast.makeText(BookAppointment.this, "Your appointment is done successfuly", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(BookAppointment.this,HomeActivity.class));
               }

            }
        });
//        btnbook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                database db = new database(getApplicationContext(), "healthcare", null, 1);
//                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                String username = sharedPreferences.getString("username", "");
//
//                if (db.checkAppointmentExists(username, fullname, address, contact, dateButton.getText().toString(), timeButton.getText().toString()) == 1) {
//                    Toast.makeText(BookAppointment.this, "Appointment Already Booked", Toast.LENGTH_SHORT).show();
//                } else {
//                    db.addOrder(username, fullname, address, contact, 0, dateButton.getText().toString(), timeButton.getText().toString(), Float.parseFloat(fees), "appointment");
//                    Toast.makeText(BookAppointment.this, "Your appointment is done successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(BookAppointment.this, HomeActivity.class));
//                }
//            }
//        });

    }
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker()
    {
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }
    @Override
    public void onBackPressed() {
        // Create the object of AlertDialog Builder class
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(BookAppointment.this);

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
        androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    }



