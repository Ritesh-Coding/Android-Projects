package com.example.retrofitdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Toast
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    // on below line we are creating variables.

 lateinit var infoRV: RecyclerView  //this is in the mainActivity
    lateinit var infoPB: ProgressBar
    lateinit var infoRVAdapter: displayAdapter
    lateinit var infoList: List<Results>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // on below line we are initializing
        // our variable with their ids.
        infoRV = findViewById(R.id.idRVCourses)
        infoPB = findViewById(R.id.idPBLoading)

        // on below line we are initializing our list
        infoList = ArrayList()

        // on below line we are calling
        // get all courses method to get data.
        getAllCourses()
    }

    private fun getAllCourses() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder().baseUrl("https://reqres.in/")
            // on below line we are calling add
            // Converter factory as Gson converter factory.
            // at last we are building our retrofit builder.
            .addConverterFactory(GsonConverterFactory.create()).build()

        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(quoteApi::class.java)

        // on below line we are calling a method to get all the courses from API.
        val call: Call<quoteList> = retrofitAPI.getallData()

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call!!.enqueue(object : Callback<quoteList> {


            override fun onResponse(call: Call<quoteList>, response: Response<quoteList>) {
                if (response.isSuccessful) {
                    infoPB.visibility = View.GONE
                    infoList = response.body()?.data!!
                }

                // on below line we are initializing our adapter.
                infoRVAdapter = displayAdapter(infoList)

                // on below line we are setting adapter to recycler view.
                infoRV.adapter = infoRVAdapter


            }

            override fun onFailure(call: Call<quoteList>, t: Throwable) {
// displaying an error message in toast
                Log.e("Error", t.message.toString())
                Toast.makeText(this@MainActivity, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
