package com.example.roomdemofinal.api

import com.example.roomdemofinal.model.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/posts")
    suspend fun getPosts(): Response<Data>
}