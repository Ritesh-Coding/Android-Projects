package com.example.understand_postapi.Network

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitService {
    @POST("users")

    fun createUser(@Body params:User)
}