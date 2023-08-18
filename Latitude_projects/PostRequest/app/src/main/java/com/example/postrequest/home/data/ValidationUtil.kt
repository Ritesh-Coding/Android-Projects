package com.example.postrequest.home.data

object ValidationUtil {
    fun validateUser(user: PostModel) : Boolean{
        if (!user.userId?.equals("")!! )
        {
            return true;
        }
        return false;
    }
}