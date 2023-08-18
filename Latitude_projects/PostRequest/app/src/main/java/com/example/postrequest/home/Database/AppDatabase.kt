package com.example.postrequest.home.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.postrequest.home.data.PostModel

@Database(entities = [PostModel::class], version = 1)
//In simple terms, you should mark your RoomDatabase class as Abstract to enable your class become flexible and skip implementing unnecessary methods of the RoomDatabase Base Class.
abstract class AppDatabase : RoomDatabase() {

      abstract  fun postDao():AppDao

    companion object {
        private  var INSTANCE: AppDatabase?=null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE==null)
            {
                INSTANCE=Room.databaseBuilder(context,AppDatabase::class.java,"POSTSDB"
                ).build()
            }
        return INSTANCE!!
        }
    }
}