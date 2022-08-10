package com.example.juda

import java.io.File

data class PostResponse(
    val data: Data,
    val title : String,
    val image : File,
    val content: String
)
{
    data class Data(
        val id : Int,
        val title : String,
        val writer_nickname : String,
        val image : String,
        val create_at : String
    )
}

