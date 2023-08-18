package com.example.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface UserDao {
    @Insert
    fun insertUser(userModel: UserModel)

    @Query("Select * from usertable")
    fun getAllUsers():List<UserModel>

    @Update
    fun updateUser(userModel: UserModel)

    @Delete
    fun deleteUser(userModel:UserModel)
}