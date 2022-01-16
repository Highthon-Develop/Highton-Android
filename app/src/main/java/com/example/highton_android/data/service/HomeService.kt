package com.example.highton_android.data.service

import com.example.highton_android.data.model.profile.DetailedUser
import com.example.highton_android.data.model.profile.UserResponse
import com.example.highton_android.data.model.recommendation.RecommendationResponse
import retrofit2.Response
import retrofit2.http.*

interface HomeService {
    @GET("/user")
    suspend fun getRecommendation(): Response<RecommendationResponse>

    @PATCH("/user")
    suspend fun followUser(@Header("Authorization") token: String, @Body targetUserIdx: Int): Response<Boolean>

    @DELETE("/user")
    suspend fun unfollowUser(@Header("Authorization") token: String, @Body targetUserIdx: Int): Response<Boolean>

    @GET("/user/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<UserResponse>
}