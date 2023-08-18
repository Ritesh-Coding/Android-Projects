package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Sign_Up : AppCompatActivity() {


    private lateinit var edtname: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnsignup : Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth=FirebaseAuth.getInstance()

        edtname=findViewById(R.id.edt_name)
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnsignup=findViewById(R.id.btn_signup)

        btnsignup.setOnClickListener{
            val name =edtname.text.toString()
            val email2 = edtEmail.text.toString()
            val password2=edtPassword.text.toString()

            signup1(name,email2,password2)
    }
    }
    private fun signup1(name:String,email2:String,password2:String)
    {
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email2,password2)
        .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
            //code for jumping to home
            addUserToDatabase(name,email2,mAuth.currentUser?.uid!!)
            val intent=Intent(this@Sign_Up,MainActivity2::class.java)
            finish()
            startActivity(intent)
        } else {
            Toast.makeText(this@Sign_Up,"Sign up successful",Toast.LENGTH_SHORT).show()
        }
    }
    }
    private fun addUserToDatabase(name: String,email2: String,uid: String)
    {
        mDbRef=FirebaseDatabase.getInstance().getReference()

        mDbRef.child("uid").child(uid).setValue(User(name,email2,uid))
    }
}