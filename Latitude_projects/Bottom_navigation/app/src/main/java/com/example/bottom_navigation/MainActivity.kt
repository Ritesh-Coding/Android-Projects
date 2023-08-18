package com.example.bottom_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottom_navigation.Fragment.Message
import com.example.bottom_navigation.Fragment.home
import com.example.bottom_navigation.Fragment.setting
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(home())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
//        bottomNav.setItemBackgroundResource(R.color.purple)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(home())
                    true
                }

                R.id.message -> {
                    loadFragment(Message())
                    true
                }

                R.id.settings -> {
                    loadFragment(setting())
                    true
                }


                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }
}