package com.example.mvvm_demo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_Instance {
    val retrofit = Retrofit.Builder().baseUrl("https://reqres.in/")
        // on below line we are calling add
        // Converter factory as Gson converter factory.
        // at last we are building our retrofit builder.
        .addConverterFactory(GsonConverterFactory.create()).build()


}