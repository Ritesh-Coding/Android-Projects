package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LOGIN : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup : Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth=FirebaseAuth.getInstance()//this is how we initialize the fireauth
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnlogin=findViewById(R.id.btn_login)
        btnsignup=findViewById(R.id.btn_signup)

        btnsignup.setOnClickListener{
            val intent = Intent(this,Sign_Up::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener{
            val email1=edtEmail.text.toString()
            val password1=edtPassword.text.toString()


            login1(email1,password1);
        }
    }
    private  fun login1(email1:String,password1:String)
    {
        //logic of login user
        mAuth.signInWithEmailAndPassword(email1, password1)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for login existing user
                    val intent=Intent(this@LOGIN,MainActivity2::class.java)
                    finish()
                    startActivity(intent)
                } else {
                   Toast.makeText(this@LOGIN,"User does not exist",Toast.LENGTH_SHORT).show()

                }
            }
    }
}