package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UserModel::class],version=1, exportSchema = false)
@TypeConverters(Converterss:: class)
abstract class AppDatabse : RoomDatabase() {
    abstract fun userDao() : UserDao //return an instance of the userDao interface..Provide a method for acessing the databse

    companion object{// It provides a way to create a singleton instance of the database using the Singleton pattern.
        private  var INSTANCE:AppDatabse?=null

        fun getInstance(context : Context):AppDatabse?
        {
            if (INSTANCE==null)
            {
                synchronized(AppDatabse::class)// This synchronized block ensures that only one thread can create the database instance at a time to avoid race conditions.
                {
                    INSTANCE= Room.databaseBuilder(context.applicationContext,AppDatabse::class.java,"userTable.db").allowMainThreadQueries().build()
                }

            }
            return INSTANCE
        }


    }
}