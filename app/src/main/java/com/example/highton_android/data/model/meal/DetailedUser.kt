package com.example.highton_android.data.model.meal

import com.google.gson.annotations.SerializedName

data class DetailedUser (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("birthDay")
    val birthDay: String,
    @SerializedName("email")
    val email: String,
    val password: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("school")
    val school: School
)
