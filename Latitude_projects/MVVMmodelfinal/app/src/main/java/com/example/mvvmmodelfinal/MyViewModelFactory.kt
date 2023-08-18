package com.example.mvvmmodelfinal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//async can return the future result which has a type of Deferred<T>,
// and we can call await() function to the Deferred variable to get the result of the Coroutine while
// launch only executes the code in the block without returning the result.
class MyViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass : Class<T>) : T
    {
        return  if (modelClass.isAssignableFrom(MainViewModel::class.java))
            {
                MainViewModel(this.repository) as T
        }
        else
        {
            throw IllegalArgumentException("ViewModel not Found")
        }

    }
}