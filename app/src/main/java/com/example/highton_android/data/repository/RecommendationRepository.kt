package com.example.highton_android.data.repository

import com.example.highton_android.data.model.recommendation.RecommendationResponse
import com.example.highton_android.data.service.RecommendationService
import retrofit2.Response
import javax.inject.Inject

class RecommendationRepository @Inject constructor(
    private val recommendationService: RecommendationService
){
    suspend fun getRecommendation(): Response<RecommendationResponse> = recommendationService.getRecommendation()
}