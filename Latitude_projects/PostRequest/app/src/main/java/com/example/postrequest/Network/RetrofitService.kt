package com.example.postrequest.Network

import com.example.postrequest.home.data.PostModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {

    //changes  here we made for the post delete
    @POST("posts")
    fun createPost(@Body postModel: PostModel): Call<PostModel>  //focus


    @GET("posts")                                              //this we have done in place of ApiInterfcae
    suspend fun getAllMovies() : Response<ArrayList<PostModel>>

    //changes here we made for the delete method
    @DELETE("posts/{id}")
     fun deletePost(@Path("id") id:Int) : Call<String>

    companion object
    {
        var retrofitService:RetrofitService?=null
        fun getInstance():RetrofitService
        {
            val logging = HttpLoggingInterceptor()         //this lines starts here
            logging.level= HttpLoggingInterceptor.Level.BODY
            var client = OkHttpClient.Builder()           //here we have implemented this to print the response in the log
            client.addInterceptor(logging)                //this line complete here


            if (retrofitService==null)
            {
                val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create()).build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}