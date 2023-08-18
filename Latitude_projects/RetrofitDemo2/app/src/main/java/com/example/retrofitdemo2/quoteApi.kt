package com.example.retrofitdemo2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Call

interface quoteApi {
    @GET("api/users")
//    suspend fun getQuotes() : Response<quoteList>
      fun getallData(): Call<quoteList>
}
