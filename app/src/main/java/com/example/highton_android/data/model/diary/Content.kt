package com.example.highton_android.data.model.diary

data class Content(
    val comments: List<Any>,
    val content: String,
    val createdAt: String,
    val emojies: List<Any>,
    val id: Int
)