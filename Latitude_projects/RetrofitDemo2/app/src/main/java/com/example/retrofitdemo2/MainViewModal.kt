//package com.example.retrofitdemo2
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.retrofitdemo2.Results
//import com.example.retrofitdemo2.ResultsModelClass
//import com.example.retrofitdemo2.quoteApi
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class MainViewModel : ViewModel() {
//    private val _userList = MutableLiveData<List<Results>>()
//    val userList: LiveData<List<Results>> get() = _userList
//
//    private val retrofitAPI: quoteApi
//
//    init {
//        // Initialize Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://reqres.in/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // Create the API interface
//        retrofitAPI = retrofit.create(quoteApi::class.java)
//    }
//
//    fun fetchData() {
//        val call: Call<Results> = retrofitAPI.getallData()
//
//        call.enqueue(object : Callback<Results> {
//            override fun onResponse(call: Call<Results>, response: Response<Results) {
//                if (response.isSuccessful) {
//                    _userList.value = response.body()?.data ?: emptyList()
//                }
//            }
//
//            override fun onFailure(call: Call<Results>, t: Throwable) {
//                // Handle error if needed
//            }
//        })
//    }
//}
