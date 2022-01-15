package com.example.highton_android.data.service

import com.example.highton_android.data.model.recommendation.RecommendationResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecommendationService {
    @GET("/user")
    suspend fun getRecommendation(): Response<RecommendationResponse>
}