package com.example.mvvm_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class DisplayAdapters(infoList: List<ResultsModelClass>) :RecyclerView.Adapter<DisplayAdapters.classViewHolder>() {
    private lateinit var displayList: List<ResultsModelClass>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DisplayAdapters.classViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(
            R.layout.userdetailslayout,
            parent,false
        )
        return classViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: DisplayAdapters.classViewHolder, position: Int) {
        holder.userFirstname.text = displayList[position].first_name
        holder.userLastname.text = displayList[position].last_name
        holder.useremail.text = displayList[position].email
        holder.userId.text = displayList[position].id.toString()  //fix

        Glide.with(holder.itemView.context)
            .load(displayList[position])
            .override(300, 200)
            .into(holder.userImage)

//        Picasso.get().load(displayList[position].avatar).into(holder.userImage)
    }

    override fun getItemCount(): Int {
        return displayList.size

    }

    class classViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userFirstname: TextView = itemView.findViewById(R.id.tvfitstname)
        val userLastname: TextView = itemView.findViewById(R.id.tvlastname)
        val userId: TextView = itemView.findViewById(R.id.tvid)
        val useremail: TextView = itemView.findViewById(R.id.tvemail)

        val userImage: ImageView = itemView.findViewById(R.id.user_image)
    }
}