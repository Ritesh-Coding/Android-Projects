package com.example.roomdemofinal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdemofinal.api.ApiInterface
import com.example.roomdemofinal.model.Data
import androidx.lifecycle.viewModelScope
class PostsRepository(private val apiInterface: ApiInterface) {

    private val postsLiveData = MutableLiveData<Data>()


    val postsDataLive: LiveData<Data>  //yha pr value tab he jayega jab viewModel se se call hoga niche wala function
        get() = postsLiveData


    suspend fun getPosts() {
        val result =
            apiInterface.getPosts()//suspended functions are the functions that can be started paused and resumed
        if (result.body() != null) {

                postsLiveData.postValue(result.body())//in postDtaLive we get the results

            }
        }

    }
