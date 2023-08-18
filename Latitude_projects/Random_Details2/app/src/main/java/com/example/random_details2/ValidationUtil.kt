package com.example.random_details2

object ValidationUtil {
    fun validateUser(user: User) : Boolean{
        if (user.first_name.isNotEmpty() && user.last_name.isNotEmpty()  && user.email.isNotEmpty() && user.avatar.isNotEmpty() && !user.id.equals(""))
        {
            return true;
        }
        return false;
    }
}