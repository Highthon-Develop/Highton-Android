package com.example.highton_android.data.model.feed.response

data class Content(
    val createdAt: String,
    val description: String,
    val emojis: List<Any>,
    val eventImages: List<Any>,
    val idx: Int,
    val title: String
)