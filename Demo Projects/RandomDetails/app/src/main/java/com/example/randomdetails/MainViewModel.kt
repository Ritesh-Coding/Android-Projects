package com.example.randomdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val movieList = MutableLiveData<List<Movie>>()

    var job: Job? = null




    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()//A live data boolean variable is set to indiacate the loading state ..when the data is fetched it is true else false

    fun getAllMovies() {  //it is responsible for fetching all movies using the main repository
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getAllMovies()) {
                is NetworkState.Success -> {
                    movieList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //movieList.postValue(NetworkState.Error())
                    } else {
                        //movieList.postValue(NetworkState.Error)
                    }
                }
            }
            loading.value=false;  //hide progress bar after displaying the data
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