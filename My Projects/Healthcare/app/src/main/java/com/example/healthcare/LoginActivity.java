package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button button;

    boolean passwordvisibility;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//
        username = findViewById(R.id.editTextLoginUsername);
        password = findViewById(R.id.editTextLoginPassword);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textViewNewUser);


        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();


                database db = new database(getApplicationContext(), "healthcare", null, 1);  //to call the database calss same as in register


                if (user_name.isEmpty() || pass_word.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill the all the detail", Toast.LENGTH_SHORT).show();

                } else {
                    if (db.login(user_name, pass_word) == 1) {  //calling function in db class
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user_name);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Register_activity.class));
            }
        });

    }
}