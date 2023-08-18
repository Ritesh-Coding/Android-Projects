package com.example.random_details2


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import android.widget.Toast
import androidx.core.content.ContentProviderCompat

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val userList: LiveData<List<User>> = MutableLiveData()
//    val userList = MutableLiveData<List<User>>()

    var job: Job? = null


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
                    (userList as MutableLiveData<List<User>>).value = response.body()?.data
                    loading.value = false
                } else {
                    onError("Something Went Wrong")

                }
            }
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

private fun <T> MutableLiveData<T>.postValue(data: List<User>) {

}
