package com.example.roomdemofinal.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
     val Base_URL = "https://jsonplaceholder.typicode.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
        .baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}