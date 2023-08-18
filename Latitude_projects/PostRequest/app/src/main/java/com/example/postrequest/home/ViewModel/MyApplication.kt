package com.example.postrequest.home.ViewModel

import android.app.Application
import com.example.postrequest.Network.RetrofitService
import com.example.postrequest.home.Database.AppDatabase
import com.example.postrequest.home.data.MainRepository

class MyApplication : Application() {

    lateinit var mainRepository: MainRepository

    override fun onCreate() {
        super.onCreate()

        var retrofitService = RetrofitService.getInstance()  //these are the lines that we have made to in place of ApiInterface

        val database = AppDatabase.getDatabase(applicationContext)
        mainRepository = MainRepository(retrofitService,database,applicationContext)
    }
}