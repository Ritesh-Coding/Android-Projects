package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var adapter: UserListAdapter
    val repo:UserRepository by lazy {
        UserRepository(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview_users)
        var  button = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        adapter=UserListAdapter(this)
       recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter

        adapter.setOnItemClick(object : ListClickListener<UserModel>{
            override fun onClick(data: UserModel, position: Int) {
                val intent = Intent(this@MainActivity,AddUser::class.java)
                intent.putExtra("user", data)
                startActivity(intent)
            }

            override fun onDelete(user: UserModel) {
                Toast.makeText(applicationContext, "Note Deleted successfuly", Toast.LENGTH_SHORT).show()
                repo.deleteUser(user)
                fetchUsers()
            }
        })


        button.setOnClickListener {
            val intent = Intent(this@MainActivity,AddUser::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchUsers()
    }

    fun fetchUsers() {
        val allUsers = repo.getAllUsers()
        adapter.setUsers(allUsers)
    }
}
