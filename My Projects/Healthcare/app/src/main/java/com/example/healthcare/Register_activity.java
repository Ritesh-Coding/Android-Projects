package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_activity extends AppCompatActivity {
    EditText username,useremail,password1,password2;
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.edittext_name);
        useremail=findViewById(R.id.edittextBMBAddress);
        password1=findViewById(R.id.edittext_BMB_pincode);
        password2=findViewById(R.id.edittext_BMB_Contactno);
        button=findViewById(R.id.Button_BMB_Booking1);
        textView=findViewById(R.id.textViewexistingUser);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_activity.this,LoginActivity.class));
            }

        });
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String userkaname=username.getText().toString();
               String userkaemail=useremail.getText().toString();
               String userkapassword=password1.getText().toString();
               String userkapassword2=password2.getText().toString();

               database db=new database(getApplicationContext(),"healthcare",null,1);  //to call the database calss

               if(userkaname.length()==0 || userkaemail.length()==0 || userkapassword.length()==0||userkapassword2.length()==0)
               {
                   Toast.makeText(Register_activity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
               }

               else
               {
                   if (!emailValidator(userkaemail))
                   {
                       Toast.makeText(Register_activity.this, "Please enter the valid email address", Toast.LENGTH_SHORT).show();
                   }
                   if (userkapassword.compareTo(userkapassword2)==0)
                   {
                        if (isValidpassword(userkapassword) && emailValidator(userkaemail)){
                            db.register(userkaname,userkaemail,userkapassword);
                            Toast.makeText(Register_activity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register_activity.this,LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Register_activity.this, "Password should contain atleast 8 character, having letter digit and special symbol", Toast.LENGTH_SHORT).show();
                        }
                   }
                   else
                   {
                       Toast.makeText(Register_activity.this, "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();
                   }
               }


           }
       });


    }
    public static boolean emailValidator(String etMail) {

        if (Patterns.EMAIL_ADDRESS.matcher(etMail).matches()) {
            return  true;
        }
        return  false;
    }
    public static boolean isValidpassword(String passwordhere)
    {
        int f1=0,f2=0,f3=0;
        if (passwordhere.length()<8)
        {
            return false;
        }
        else{
            for (int p=0;p<passwordhere.length();p++){
                if (Character.isLetter(passwordhere.charAt(p)));{
                    f1=1;
                }
            }
            for (int r=0;r<passwordhere.length();r++)
            {
                if (Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for (int s=0;s<passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if (c>=33 && c<=46||c==64){
                    f3=1;
                }
            }
            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
//        startActivity(new Intent(Register_activity.this, LoginActivity.class));
//        finish();

    }
}