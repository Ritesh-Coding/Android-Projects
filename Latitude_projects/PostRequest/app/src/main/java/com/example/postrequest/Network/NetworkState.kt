package com.example.postrequest.Network


import retrofit2.Response

sealed class NetworkState<out T> {
    data class Success<out T>(val data: T): NetworkState<T>()
    data class Error<T>(val response: Response<T>): NetworkState<T>()//Error<T>: Represents an error network response and holds the Response<T> object, which contains information about the error (e.g., HTTP status code, error body).
}
//parseResponse :-->  This extension function is defined for Retrofit's Response<T> class. It converts the Retrofit Response object into a NetworkState object. The function performs the following checks:
//If the response is successful (HTTP status code 2xx) and the response body is not null, it returns a NetworkState.Success object containing the successful data from the response body.
//If the response is not successful (HTTP status code other than 2xx) or the response body is null, it returns a NetworkState.Error object containing the original Response object.

fun <T> Response<T>.parseResponse(): NetworkState<T> {    //This structure is used to represent the result of a network operation and handle success and error states in a standardized way.
    return if (this.isSuccessful && this.body() != null) {//Success<T>: Represents a successful network response and holds the successful data of type T.
        val responseBody = this.body()
        NetworkState.Success(responseBody!!)
    } else {

        NetworkState.Error(this)
    }
}

