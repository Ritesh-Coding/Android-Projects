package com.example.roomdatabase
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold


class UserListAdapter(var context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    var userList = mutableListOf<UserModel>()
    var clickListener: ListClickListener<UserModel>? = null
//    private lateinit var binding: ActivityAddUserBinding

    fun setUsers(users: List<UserModel>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]

        holder.location1?.text=user.location
        holder.username1?.text = user.username
        holder.email1?.text = user.email
        holder.layout1?.setOnClickListener {
            clickListener?.onClick(user,position)
        }

        holder.imgDelete1.setOnClickListener {
            clickListener?.onDelete(user)
        }
    }

    override fun getItemCount(): Int {
        return userList.size


    }
    fun setOnItemClick(listClickListener: ListClickListener<UserModel>) {
        this.clickListener = listClickListener
    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val username1 = view.findViewById<TextView>(R.id.text_username)
    val location1 = view.findViewById<TextView>(R.id.text_location)
    val email1 = view.findViewById<TextView>(R.id.text_email)
    val layout1 = view.findViewById<ConstraintLayout>(R.id.mailayout)
    val imgDelete1 = view.findViewById<AppCompatImageView>(R.id.imgDelete)
}

interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
    fun onDelete(user: T)
}
