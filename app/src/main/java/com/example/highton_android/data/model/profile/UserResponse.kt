package com.example.highton_android.data.model.profile


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("content")
    val content: DetailedUser
)