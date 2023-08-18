package com.example.postrequest.home.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostsTable")
data class PostModel(
    var userId:Int?=0,
    @PrimaryKey
    var id:Int?=0,
    var title:String?="",
    var body:String?=""
)
