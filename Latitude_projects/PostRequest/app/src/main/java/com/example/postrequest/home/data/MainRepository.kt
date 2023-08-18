package com.example.postrequest.home.data

import android.content.Context
import android.database.Cursor
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.postrequest.Network.RetrofitService
import com.example.postrequest.Utility.InternetUtility
import com.example.postrequest.home.Database.AppDatabase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainRepository(
    private val retrofitService: RetrofitService,
    private val appDatabase: AppDatabase,
    private val applicationContext: Context
) {

    suspend fun getAllMovies() = retrofitService.getAllMovies()


    //I have inserted this to get all the details into the room database
//    private  val postsLiveData = MutableLiveData<ArrayList<PostModel>>()    // Response<ArrayList<PostModel>>
    public val postsLiveData = MutableLiveData<List<PostModel>>()
                                                                          // Response<ArrayList<PostModel>>

//    val posts1: MutableLiveData<ArrayList<PostModel>>
//        get() = postsLiveData   //yha p live data store ho jayega

    //changes made
    fun createPost(postModel: PostModel): LiveData<PostModel> {
        val data = MutableLiveData<PostModel>()!!

        retrofitService.createPost(postModel).enqueue(object : Callback<PostModel> {
            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                val res = response.body()
                if (response.code() == 201 && res != null) {

                    data.value = res

                } else {
                    data.value = null
                }
            }
        })
        return data
    }

    var job: Job? = null






    /*fun getallPosts() {
        job = CoroutineScope(Dispatchers.IO).launch {
            if (InternetUtility.isInterntAvailable(applicationContext)) {
                val result = retrofitService.getAllMovies()

//        val result  = retrofitService.getAllMovies()
                if (result.body() != null) {
                    postsLiveData.postValue(result.body())
                    Log.d("resultok",result.toString())
                    appDatabase.postDao().insertPosts(result.body())
                    Log.d("result1",appDatabase.postDao().getallPosts().toString())
                }
            } else {
                val offlineposts = appDatabase.postDao().getallPosts()
                postsLiveData.postValue(offlineposts)
                Log.d("resultin","result"+postsLiveData.toString())
            }
        }
    }*/

    fun  getPosts(): List<PostModel>
    {
        return  appDatabase.postDao().getallPosts()
    }

    fun saveDataInDb(arrayList:ArrayList<PostModel>?){
        appDatabase.postDao().insertPosts(arrayList)
        //Log.d("saveData>>>","-------------------------------------------------------------")
        //Log.d("saveData>>>","saveData>>> ${appDatabase.postDao().getallPosts().toString()}")
    }


    //new function we made for the delete post
    fun deletePost(id: Int): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        retrofitService.deletePost(id).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("response", Gson().toJson(data.value))

                data.value = response.code() == 200
            }
        })
        return data;


    }

}