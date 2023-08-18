package com.example.roomdemofinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemofinal.model.Data
import com.example.roomdemofinal.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel(private val PostRepository : PostsRepository): ViewModel() {

            init {
                viewModelScope.launch(Dispatchers.IO) {  //ye jaise he call hoga iska method run hoga and vha jayega
                    PostRepository.getPosts()
                }

            }
    val posts:LiveData<Data>
        get()=PostRepository.postsDataLive

}