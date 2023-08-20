package com.example.random_details2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.random_details2.databinding.AdapterMovieBinding
import com.example.random_details2.databinding.AdapterUserBinding

class UserAdadpter : RecyclerView.Adapter<MainViewHolder>() {

    private var userList = mutableListOf<User>()

    fun setUsers(users: List<User>) {
        this.userList = users as MutableList<User>   //here I made a change
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterUserBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.tvfitstname.text = user.first_name
        holder.binding.tvlastname.text = user.last_name
        holder.binding.tvDIid.text = user.id.toString()
        holder.binding.tvemail.text = user.email

        Glide.with(holder.itemView.context).load(user.avatar).into(holder.binding.userImage)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class MainViewHolder(val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {
// This class represents the ViewHolder for each item in the RecyclerView. It takes an AdapterMovieBinding object as a parameter, which is used to access the views in the movie item layout (adapter_movie.xml).
}