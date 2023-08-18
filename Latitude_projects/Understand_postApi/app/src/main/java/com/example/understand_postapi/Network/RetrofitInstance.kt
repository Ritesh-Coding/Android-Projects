package com.example.understand_postapi.Network

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//we have created this first

class RetrofitInstance {

    companion object
    {
        val Base_url="https://gorest.co.in/public-api/"

        fun getRetrofitInstance():Retrofit{
            val logging = HttpLoggingInterceptor()         //this lines starts here
            logging.level=HttpLoggingInterceptor.Level.BODY
            var client = OkHttpClient.Builder()           //here we have implemented this to print the response in the log
            client.addInterceptor(logging)                //this line complete here



            return Retrofit.Builder()
                .baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }

}