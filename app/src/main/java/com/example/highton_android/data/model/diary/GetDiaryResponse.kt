package com.example.highton_android.data.model.diary

data class GetDiaryResponse(
    val content: List<Content>,
    val success: Boolean
)