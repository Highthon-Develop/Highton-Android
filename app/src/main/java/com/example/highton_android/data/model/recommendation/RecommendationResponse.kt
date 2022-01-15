package com.example.highton_android.data.model.recommendation


import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("content")
    val content: List<User>
)