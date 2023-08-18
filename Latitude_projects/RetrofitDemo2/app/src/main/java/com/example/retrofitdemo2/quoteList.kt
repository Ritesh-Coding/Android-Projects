package com.example.retrofitdemo2

data class quoteList(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val data: List<Results>,

    val totalPages: Int
)

