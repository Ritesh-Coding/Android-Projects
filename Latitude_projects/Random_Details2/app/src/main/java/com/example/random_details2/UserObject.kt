package com.example.random_details2

data class UserObject(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val data: List<User>,

    val totalPages: Int
)
