package com.example.roomdemofinal.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemofinal.repository.PostsRepository

class MemesViewModelFactory(private val PostsRepository : PostsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(PostsRepository) as T
    }
}