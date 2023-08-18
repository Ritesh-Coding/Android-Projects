package com.example.roomdatabase

import android.content.Context
import android.os.AsyncTask

class UserRepository(context : Context) {

    var db: UserDao = AppDatabse.getInstance(context)?.userDao()!!

    //Fetch all the users
    fun getAllUsers(): List<UserModel> {
        return db.getAllUsers()
    }

    //Insert new user
    fun insertUser(userModel: UserModel) {
        insertAsynTask(db).execute(userModel)
    }

    // Delete user
    fun deleteUser(users: UserModel) {
        db.deleteUser(users)
    }


    //update user
    fun updateUser(userModel: UserModel) {
        db.updateUser(userModel)
    }


    private class insertAsynTask internal constructor(private val userDao: UserDao) :
        AsyncTask<UserModel, Void, Void>() {
        override fun doInBackground(vararg params: UserModel): Void?
            {
                userDao.insertUser(params[0])
                return null
            }

        }


    }
