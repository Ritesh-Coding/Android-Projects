package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater

import android.widget.Toast
import com.example.roomdatabase.databinding.ActivityAddUserBinding

class AddUser : AppCompatActivity() {
    var user: UserModel? = null
    private lateinit var binding: ActivityAddUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        user = intent.getParcelableExtra("user")
        user?.let {
            binding.edUsername.setText(it.username)
            binding.edLocation.setText(it.location)
            binding.edEmail.setText(it.email)


        } ?: kotlin.run{}

        val repo = UserRepository(this)

        binding.buttonSaveUser.setOnClickListener {


            if (binding.edUsername.text.isNotEmpty() && binding.edEmail.text.isNotEmpty() && binding.edLocation.text.isNotEmpty()) {
                user?.let {
                    val user = UserModel(
                        userId = it.userId,
                        username = binding.edUsername.text.toString(),
                        location = binding.edLocation.text.toString(),
                        email = binding.edEmail.text.toString(),
                    )
                    repo.updateUser(user)
                    Toast.makeText(this, "Note Updated ", Toast.LENGTH_SHORT).show()
                } ?: run {
                    val user = UserModel(
                        username = binding.edUsername.text.toString(),
                        location = binding.edLocation.text.toString(),
                        email = binding.edEmail.text.toString()
                    )
                    repo.insertUser(user)
                    Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
