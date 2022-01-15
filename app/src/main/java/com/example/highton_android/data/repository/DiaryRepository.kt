package com.example.highton_android.data.repository

import com.example.highton_android.data.model.diary.GetDiaryResponse
import com.example.highton_android.data.model.diary.PostDiaryRequest
import com.example.highton_android.data.model.diary.PostDiaryResponse
import com.example.highton_android.data.service.DiaryService
import retrofit2.Response
import javax.inject.Inject

class DiaryRepository @Inject constructor(
    private val service: DiaryService
) {
    suspend fun getDiary(
        token: String
    ): Response<GetDiaryResponse> {
        return service.getDiary(token)
    }

    suspend fun postDiary(
        body: PostDiaryRequest
    ): Response<PostDiaryResponse> {
        return service.postDiary(body)
    }
}