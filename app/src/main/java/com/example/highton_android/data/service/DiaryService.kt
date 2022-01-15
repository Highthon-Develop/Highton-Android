package com.example.highton_android.data.service

import com.example.highton_android.data.model.diary.GetDiaryResponse
import com.example.highton_android.data.model.diary.PostDiaryRequest
import com.example.highton_android.data.model.diary.PostDiaryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DiaryService {

    @GET("diary")
    suspend fun getDiary(
        @Header("authorization") token: String
    ): Response<GetDiaryResponse>

    @POST("diary")
    suspend fun postDiary(
        @Body body: PostDiaryRequest
    ): Response<PostDiaryResponse>
}