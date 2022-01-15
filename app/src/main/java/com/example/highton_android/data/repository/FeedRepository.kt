package com.example.highton_android.data.repository

import com.example.highton_android.data.model.feed.request.PostSchoolEventRequest
import com.example.highton_android.data.model.feed.response.GetSchoolEventResponse
import com.example.highton_android.data.model.feed.response.PostSchoolEventResponse
import com.example.highton_android.data.service.FeedService
import retrofit2.Response
import javax.inject.Inject

class FeedRepository @Inject constructor(private val api: FeedService) {
    suspend fun postSchoolEvent(
        token: String,
        body: PostSchoolEventRequest

    ): Response<PostSchoolEventResponse> {
        return api.postSchoolEvent(token, body)
    }

    suspend fun getSchoolEvent(

        page: Int,
        criteria: String,
    ): Response<GetSchoolEventResponse> {
        return api.getSchoolEvent(page, criteria)
    }

}