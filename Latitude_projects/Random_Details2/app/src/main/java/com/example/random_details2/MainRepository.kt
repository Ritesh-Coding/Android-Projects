package com.example.random_details2


class MainRepository constructor(private val retrofitService: RetrofitService) {


    suspend fun getAllMovies() = retrofitService.getAllMovies()

//    suspend fun getAllMovies() : List<User> {
//        val response = retrofitService.getAllMovies()
//        return if (response.isSuccessful) {
//            val responseBody = response.body()
//            if (responseBody != null) {
//                NetworkState.Success(responseBody)
//            } else {
//                NetworkState.Error(response)
//            }
//        } else {
//            NetworkState.Error(response)
//        }
//    }

}