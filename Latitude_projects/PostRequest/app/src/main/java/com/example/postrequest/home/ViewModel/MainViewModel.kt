package com.example.postrequest.home.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postrequest.home.data.MainRepository
import com.example.postrequest.home.data.PostModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.example.postrequest.home.Database.AppDatabase

//import com.example.postrequest.home.Database.getDatabase
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    var createPostLiveData:LiveData<PostModel>?=null //here we add the observer to add the data for the mutable data live
    var deletePostLiveData:LiveData<Boolean>?=null



    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
    get() = _errorMessage
    val userList: LiveData<ArrayList<PostModel>> = MutableLiveData()


////   changes we have done to insert the data into the room database
//        init {
//            viewModelScope.launch(Dispatchers.IO) {
//                mainRepository.getallPosts()
//            }
//        }
////    val posts12 : LiveData<ArrayList<PostModel>>
//
////    get() = mainRepository.posts1         //yha se invoke hu



    var job: Job? = null

    //changes
   init {
        createPostLiveData=MutableLiveData()       //we have initialize this
        deletePostLiveData=MutableLiveData()
   }
    //changes done

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading =
        MutableLiveData<Boolean>()//A live data boolean variable is set to indiacate the loading state ..when the data is fetched it is true else false



    fun getAllMovies() {  //it is responsible for fetching all movies using the main repository
        Log.d("Thread Outside", Thread.currentThread().name)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllMovies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    (userList as MutableLiveData<List<PostModel>>).value = response.body()
                    viewModelScope.launch(Dispatchers.IO) {
                        mainRepository.saveDataInDb(response.body())
                    }
                    loading.value = false
                } else {
                    onError("something went wrong ...")
                }
            }
        }
    }

    //changes made
    fun createPost(postModel: PostModel) {    //we have to use this launch because we call it from the mainRepository  thus its return value in createPost
        viewModelScope.launch { // Use viewModelScope.launch to launch a coroutine in ViewModel scope
            createPostLiveData = mainRepository.createPost(postModel)
        }
    }

    fun  getPosts()
    {
        viewModelScope.launch(Dispatchers.IO) {
            val dbUserList = mainRepository.getPosts()
            (userList as MutableLiveData<List<PostModel>>).postValue(dbUserList)
        }


    }

    fun deletePost(id:Int)
    {
        viewModelScope.launch {
            deletePostLiveData = mainRepository.deletePost(id)
        }
    }

    private fun onError(message: String) {  //A private function used to set the error message and update the loading state when an exception occurs during the data retrieval process.
        _errorMessage.value = message
        loading.value = false

    }

    override fun onCleared() {//onCleared(): An overridden method from ViewModel, which is called when the ViewModel is no longer in use. It cancels any ongoing coroutines to prevent unnecessary work or memory leaks.
        super.onCleared()
        job?.cancel()
    }



}