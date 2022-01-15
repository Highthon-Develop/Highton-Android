package com.example.highton_android.data.repository

import com.example.highton_android.data.model.meal.DetailedUser
import com.example.highton_android.data.model.recommendation.RecommendationResponse
import com.example.highton_android.data.model.recommendation.User
import com.example.highton_android.data.service.HomeService
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeService: HomeService
){
    suspend fun getRecommendation(): Response<RecommendationResponse> = homeService.getRecommendation()

    suspend fun getProfile(token: String): Response<DetailedUser> = homeService.getProfile(token)
}