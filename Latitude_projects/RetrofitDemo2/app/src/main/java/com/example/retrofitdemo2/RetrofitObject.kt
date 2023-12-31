package com.example.retrofitdemo2
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitObject {

    val baseUrl = "https://reqres.in/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}
