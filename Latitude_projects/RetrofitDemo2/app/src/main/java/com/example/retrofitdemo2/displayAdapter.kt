package com.example.retrofitdemo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class displayAdapter(
        private var displayList : List<Results>,
):RecyclerView.Adapter<displayAdapter.classViewHolder>() {

//    fun setUserList(displayList: List<Results>){
//        this.displayList = displayList as ArrayList<Results>
//        notifyDataSetChanged()
//    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): displayAdapter.classViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.display_items,
            parent, false
        )
        return classViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: displayAdapter.classViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
//        holder.userFirstname.text = displayList.get(position)
//        holder.userLastname.text=displayList.get(position).lastname
//        holder.userId.text=displayList.get(position).id
//        holder.useremail.text=displayList.get(position).email

        holder.userFirstname.text = displayList[position].first_name
        holder.userLastname.text = displayList[position].last_name
        holder.useremail.text = displayList[position].email
        holder.userId.text = displayList[position].id.toString()  //fix


        Picasso.get().load(displayList[position].avatar).into(holder.userImage)
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return displayList.size
    }
    class classViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our
        // course name text view and our image view.
        val userFirstname: TextView = itemView.findViewById(R.id.tvfitstname)
        val userLastname: TextView = itemView.findViewById(R.id.tvlastname)
        val userId: TextView = itemView.findViewById(R.id.tvDIid)
        val useremail: TextView = itemView.findViewById(R.id.tvemail)

        val userImage: ImageView = itemView.findViewById(R.id.user_image)
    }

}