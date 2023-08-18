package com.example.postrequest.home.Database


import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postrequest.home.data.PostModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //this we have done so that when conflict is there it will resolve the error
    fun insertPosts(postModel: ArrayList<PostModel>?)

   @Query("SELECT * FROM poststable")
    fun getallPosts(): List<PostModel>


}