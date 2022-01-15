package com.example.highton_android.data.service

import com.example.highton_android.data.model.feed.request.PostSchoolEventRequest
import com.example.highton_android.data.model.feed.response.GetSchoolEventResponse
import com.example.highton_android.data.model.feed.response.PostSchoolEventResponse
import retrofit2.Response
import retrofit2.http.*

interface FeedService {

    @POST("school/event")
    suspend fun postSchoolEvent(
        @Header("authorization") token: String,
        @Body body: PostSchoolEventRequest

    ): Response<PostSchoolEventResponse>

    @GET("school/event")
    suspend fun getSchoolEvent(

        @Query("page") page: Int,
        @Query("criteria") criteria: String,
    ): Response<GetSchoolEventResponse>
}