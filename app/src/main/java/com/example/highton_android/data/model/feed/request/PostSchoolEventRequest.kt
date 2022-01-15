package com.example.highton_android.data.model.feed.request

data class PostSchoolEventRequest(
    val description: String,
    val imgUrlList: List<String>,
    val schoolIdx: Int,
    val title: String
)