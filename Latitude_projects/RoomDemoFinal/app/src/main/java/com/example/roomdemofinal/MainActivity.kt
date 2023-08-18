package com.example.roomdemofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemofinal.api.ApiInterface
import com.example.roomdemofinal.api.ApiUtilities
import com.example.roomdemofinal.repository.PostsRepository
import com.example.roomdemofinal.viewModel.MemesViewModelFactory
import com.example.roomdemofinal.viewModel.PostsViewModel

class MainActivity : AppCompatActivity() {
    private  lateinit var  postsViewModel: PostsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val postRepository = PostsRepository(apiInterface)

        postsViewModel= ViewModelProvider(this,MemesViewModelFactory(postRepository)).get(PostsViewModel::class.java)

        postsViewModel.posts.observe(this,{
            Log.d("dolorem","onCreatejk:${it.toString()}")
        })


    }
}