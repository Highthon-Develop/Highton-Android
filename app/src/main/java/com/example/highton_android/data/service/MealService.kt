package com.example.highton_android.data.service

import com.example.mealgo.data.meal.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MealService {
    @GET("hub/mealServiceDietInfo")
    suspend fun getMealList(@QueryMap queries: Map<String, String>): Response<MealResponse>
}