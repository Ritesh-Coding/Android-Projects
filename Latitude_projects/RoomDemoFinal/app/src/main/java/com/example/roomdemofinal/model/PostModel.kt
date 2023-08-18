package com.example.roomdemofinal.model

import androidx.room.PrimaryKey

data class PostModel(
    @PrimaryKey
    var userId:Int?=0,
    var id:Int?=0,
    var title:String?="",
    var body:String?=""
)
