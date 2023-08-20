package com.example.postrequest.home.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.postrequest.databinding.HomeRvItemViewBinding

import com.example.postrequest.home.data.PostModel

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.NonDisposableHandle.parent


class UserAdadpter(var listner: HomeListener,var context: Context) : RecyclerView.Adapter<MainViewHolder>() {

    private var userList = mutableListOf<PostModel>()
    private var data: ArrayList<PostModel>  = arrayListOf() //change2

    fun setUsers(users: ArrayList<PostModel>) {
        this.userList = users as MutableList<PostModel>   //here I made a change
        data = users                                     //chnage 1
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeRvItemViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var user = userList[position]

        holder.binding.userId.text = user.userId.toString()
        holder.binding.tvId.text = user.id.toString()
        holder.binding.tvTitle.text = user.title
        holder.binding.userBody.text = user.body


        //to delete the items
        val item = data?.get(position)
        holder.bindView(item)

        //here I implement the dialog box to show the alert to the user

        holder.binding.IvHomeRV.setOnClickListener {

            var builder = AlertDialog.Builder(context)

            builder.setMessage("Are you sure You want to delete?")

            builder.setTitle("Confirm Deletion")

            builder.setCancelable(false)

            builder.setPositiveButton("Yes")
            {
                dialog,which-> item?.let { it1 ->
                listner.onItemDeleted(it1, position)
                    }
            }

            builder.setNegativeButton("No")
            {
                dialog,which->dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()




        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    //changes
    fun addData(postModel: PostModel) {
        data.add(0, postModel)
        notifyItemInserted(0)
    }
    fun removeData(position: Int)
    {
        data.removeAt(position)
        notifyDataSetChanged()
    }
    ////we need to add a listener in our adapter class which will tell our Main Activity, which post needs to delete. So now add a listener in adapter.s

    interface HomeListener{
        fun onItemDeleted(postModel: PostModel, position: Int)
    }

}//changes made  in brackets



class MainViewHolder(val binding: HomeRvItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindView(item: PostModel?) {
binding.tvTitle.text=item?.title
        binding.userBody.text=item?.body
    }
// This class represents the ViewHolder for each item in the RecyclerView. It takes an AdapterMovieBinding object as a parameter, which is used to access the views in the movie item layout (adapter_movie.xml).
}

