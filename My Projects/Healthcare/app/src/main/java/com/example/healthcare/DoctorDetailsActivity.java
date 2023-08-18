package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1={    {"Doctor Name: Ritesh Jaiswal","Hospital Address : Ahmedabad","EXP : 6yrs","Mobile No:7573060549","600"},
                                            {"Doctor Name: Mohit Jaiswal","Hospital Address : Nikol","EXP : 10yrs","Mobile No:5656665699","700"},
                                            {"Doctor Name: Mohan Pandey","Hospital Address : Bavla","EXP : 2yrs","Mobile No:7983060549","300"},
                                            {"Doctor Name: Chirag Advani","Hospital Address : Ahmedabad","EXP : 15yrs","Mobile No:892635695","900"},
                                            {"Doctor Name: Lokesh Brahmbhatt","Hospital Address : Paldi","EXP : 6yrs","Mobile No:894078955","500"}
                                        };
    private String[][] doctor_details2={    {"Doctor Name: Sonu Jaiswal","Hospital Address : Ahmedabad","EXP : 6yrs","Mobile No:7573060549","600"},
                                            {"Doctor Name: Pankaj Jaiswal","Hospital Address : Nikol","EXP : 10yrs","Mobile No:5656665699","700"},
                                            {"Doctor Name: Mohan Pandey","Hospital Address : Bavla","EXP : 2yrs","Mobile No:7983060549","300"},
                                            {"Doctor Name: Chirag Advani","Hospital Address : Ahmedabad","EXP : 15yrs","Mobile No:892635695","900"},
                                            {"Doctor Name: Lokesh Brahmbhatt","Hospital Address : Paldi","EXP : 6yrs","Mobile No:894078955","500"}
                                        };
    private String[][] doctor_details3={    {"Doctor Name: Lovesh Jaiswal","Hospital Address : Ahmedabad","EXP : 6yrs","Mobile No:7573060549","600"},
                                            {"Doctor Name: Mohit Jaiswal","Hospital Address : Nikol","EXP : 10yrs","Mobile No:5656665699","700"},
                                            {"Doctor Name: Mohan Pandey","Hospital Address : Bavla","EXP : 2yrs","Mobile No:7983060549","300"},
                                            {"Doctor Name: Chirag Advani","Hospital Address : Ahmedabad","EXP : 15yrs","Mobile No:892635695","900"},
                                            {"Doctor Name: Lokesh Brahmbhatt","Hospital Address : Paldi","EXP : 6yrs","Mobile No:894078955","500"}
                                        };
    private String[][] doctor_details4={    {"Doctor Name: Vikas Jaiswal","Hospital Address : Ahmedabad","EXP : 6yrs","Mobile No:7573060549","600"},
                                            {"Doctor Name: Mohit Jaiswal","Hospital Address : Nikol","EXP : 10yrs","Mobile No:5656665699","700"},
                                            {"Doctor Name: Mohan Pandey","Hospital Address : Bavla","EXP : 2yrs","Mobile No:7983060549","300"},
                                            {"Doctor Name: Chirag Advani","Hospital Address : Ahmedabad","EXP : 15yrs","Mobile No:892635695","900"},
                                            {"Doctor Name: Lokesh Brahmbhatt","Hospital Address : Paldi","EXP : 6yrs","Mobile No:894078955","500"}
                                        };
    private String[][] doctor_details5={    {"Doctor Name: Sandeep Jaiswal","Hospital Address : Ahmedabad","EXP : 6yrs","Mobile No:7573060549","600"},
                                            {"Doctor Name: Mohit Jaiswal","Hospital Address : Nikol","EXP : 10yrs","Mobile No:5656665699","700"},
                                            {"Doctor Name: Mohan Pandey","Hospital Address : Bavla","EXP : 2yrs","Mobile No:7983060549","300"},
                                            {"Doctor Name: Chirag Advani","Hospital Address : Ahmedabad","EXP : 15yrs","Mobile No:892635695","900"},
                                            {"Doctor Name: Lokesh Brahmbhatt","Hospital Address : Paldi","EXP : 6yrs","Mobile No:894078955","500"}
                                        };
    TextView tv;
    Button btn;
    String[][] doctor_details={};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewLT);
        btn=findViewById(R.id.Button_BM_cart_Back);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;

        else
        if (title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;

         else
        if (title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;


        else
        if (title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;


        else
        if (title.compareTo("Cardiologist")==0)
            doctor_details=doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list=new ArrayList();
        for (int i=0;i<doctor_details.length;i++)  //here we map the text
        {
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]); //accoring to the column we add the data into the list
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);

        }
        //from list we fetch the data
        sa=new SimpleAdapter(this,list,                  //here we map the button
                R.layout.multil_ines,  //multilines is the design
                new String[]{"line1","line2","line3","line4","line5"},  //from line a,b...
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e} //to   these id
        );

        ListView lst=findViewById(R.id.listviewBM);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //bu clicking how to show in Book Appointment
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointment.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[position][0]);
                it.putExtra("text3",doctor_details[position][1]);
                it.putExtra("text4",doctor_details[position][3]);
                it.putExtra("text5",doctor_details[position][4]);
                startActivity(it);
            }
        });

   }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
        finish();

    }
}