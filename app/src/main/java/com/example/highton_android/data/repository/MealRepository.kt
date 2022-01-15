package com.example.highton_android.data.repository

import com.example.highton_android.data.service.MealService
import com.example.mealgo.data.meal.model.MealResponse
import retrofit2.Response
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val mealService: MealService
) {
    suspend fun getMeal(queries: Map<String, String>): Response<MealResponse> = mealService.getMealList(queries)
}