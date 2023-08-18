package com.example.retrofit_latitude

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.provider.Settings.Global
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesApi =RetrofitHelper.getInstance().create(QuotesApi::class.java)
        //launching a new coroutine
        GlobalScope.launch {
            val result=quotesApi.getQuotes()
            if (result != null)
            // Checking the results
                Log.d("ritesh", result.body().toString())
        }
        }
    }
