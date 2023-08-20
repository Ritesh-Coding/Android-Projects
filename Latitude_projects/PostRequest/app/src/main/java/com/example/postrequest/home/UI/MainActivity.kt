package com.example.postrequest.home.UI

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.postrequest.Network.RetrofitService
import com.example.postrequest.R
import com.example.postrequest.databinding.ActivityMainBinding
import com.example.postrequest.home.Database.AppDatabase
//import com.example.postrequest.home.Database.INSTANCE
import com.example.postrequest.home.ViewModel.MainViewModel
import com.example.postrequest.home.ViewModel.MyApplication
import com.example.postrequest.home.ViewModel.MyViewModelFactory
import com.example.postrequest.home.data.MainRepository
import com.example.postrequest.home.data.PostModel



class MainActivity : AppCompatActivity(),UserAdadpter.HomeListener{
    lateinit var viewModel: MainViewModel
    private val adapter = UserAdadpter(this,this)
    lateinit var binding: ActivityMainBinding

    //changes
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_create_post -> showCreatePOstDialog()
        }
        return true
    }


    private fun showCreatePOstDialog() {
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.create_post_dialog, null)
        val btn_submit1=view.findViewById<Button>(R.id.btn_submit)
        val et_title1=view.findViewById<EditText>(R.id.et_title)
        val et_body1=view.findViewById<EditText>(R.id.et_body)

        dialog.setContentView(view)
        var title = ""
        var body = ""
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = CreatePostDialogBinding.inflate(inflater, view, false)
////        return MainViewHolder(binding)

        btn_submit1.setOnClickListener {
            title = et_title1.text.toString().trim()
            body = et_body1.text.toString().trim()

            if (title.isNotEmpty() && body.isNotEmpty()){
                val postModel = PostModel()
                postModel.userId = 1
                postModel.title = title
                postModel.body = body

                viewModel.createPost(postModel)

                viewModel.createPostLiveData?.observe(this, Observer {
                    if (it!=null){
                        adapter.addData(postModel)
                        viewModel.createPost(postModel)
                        binding.recyclerview.smoothScrollToPosition(0)
                        Toast.makeText(this,"post added",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this.applicationContext,"Cannot create post at the moment",Toast.LENGTH_SHORT).show()
                    }
                    dialog.cancel()
                })

            }else{

                Toast.makeText(this.applicationContext,"Please fill data carefully",Toast.LENGTH_SHORT).show()
            }


        }

        dialog.show()

        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        var retrofitService = RetrofitService.getInstance()  //these are the lines that we have made to in place of ApiInterface
//       var mainRepository = MainRepository(retrofitService)  //for these we have created only that of the AppDatabse
//                                |
//                                |
        //we have extended the mainRepository be the making the class as follows
        val repository= (application as MyApplication).mainRepository


        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(this, MyViewModelFactory(repository)).get(MainViewModel::class.java)


        viewModel.userList.observe(this) {
//            Log.d("response","onCreate ${it.toString()}")
//            Toast.makeText(this,"Size : ${it.size}",Toast.LENGTH_LONG).show()
            adapter.setUsers(it)
        }


        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }


        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })



        if (isNetwork(getApplicationContext())){
//            Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show()
            viewModel.getAllMovies()
        } else {
            Toast.makeText(getApplicationContext(), "Internet Is Not Connected", Toast.LENGTH_SHORT).show()
            viewModel.getPosts()

        }


    }
     override  fun onItemDeleted(postModel: PostModel,position :Int)
    {
        postModel.id?.let { viewModel.deletePost(it) }
        viewModel.deletePostLiveData?.observe(this, Observer {
            if (it!=null)
            {
                adapter.removeData(position)
            }
            else
            {
                Toast.makeText(this.applicationContext,"Cannot delete post at the moment",Toast.LENGTH_SHORT)
            }
        })
    }

    fun isNetwork(context: Context): Boolean {
        val cm = context
            .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else false
    }
}