package com.example.random_details2

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("api/users")
    suspend fun getAllMovies() : Response<UserObject>

    companion object
    {
        var retrofitService:RetrofitService?=null
        fun getInstance():RetrofitService
        {
            if (retrofitService==null)
            {
                val retrofit = Retrofit.Builder().baseUrl("https://reqres.in/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}