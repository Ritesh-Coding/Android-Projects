package com.example.mvvm_demo

import retrofit2.Call
import retrofit2.http.GET

interface User_DetailsApi {
    @GET("api/users")

    fun getallData(): Call<UserModelclass1>

}