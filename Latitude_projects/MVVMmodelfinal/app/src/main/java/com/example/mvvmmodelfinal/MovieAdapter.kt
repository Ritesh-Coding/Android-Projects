package com.example.mvvmmodelfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.constraintlayout.utils.widget.MockView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.mvvmmodelfinal.databinding.AdapterMovieBinding


class MovieAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var movieList=mutableListOf<Movie>()
    fun setMovies(movies:List<Movie>)  //update the list with the new movie data and trigger a data set changed notofication
    {
        this.movieList=movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflator= LayoutInflater.from(parent.context)
        val  binding =AdapterMovieBinding.inflate(inflator,parent,false)  //from the attached adapter movie.xml it inflates item
        return MainViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {//This method is called for each item in the movieList, and it binds the data to the corresponding MainViewHolder
        val movie= movieList[position]
        if(ValidationUtil.validateMovie(movie))
        {
            holder.binding.name.text=movie.name
            Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
        }

    }

    override fun getItemCount(): Int {
            return movieList.size
    }


}
class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {
// This class represents the ViewHolder for each item in the RecyclerView. It takes an AdapterMovieBinding object as a parameter, which is used to access the views in the movie item layout (adapter_movie.xml).
}