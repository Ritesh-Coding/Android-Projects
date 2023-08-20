package com.example.roomdatabase

import android.content.Intent

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

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
                // Create the object of AlertDialog Builder class
                val builder = AlertDialog.Builder(this@MainActivity)

                // Set the message show for the Alert time
                builder.setMessage("Do you want to exit ?")

                // Set Alert Title
                builder.setTitle("Confirm Deletion")

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false)

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("Yes") {
                    // When the user click yes button then app will close
                        dialog, which ->  Toast.makeText(applicationContext, "Note Deleted successfuly", Toast.LENGTH_SHORT).show()
                    repo.deleteUser(user)
                    fetchUsers()
                }

                // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("No") {
                    // If user click no then dialog box is canceled.
                        dialog, which -> dialog.cancel()
                }

                // Create the Alert dialog
                val alertDialog = builder.create()
                // Show the Alert Dialog box
                alertDialog.show()

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
