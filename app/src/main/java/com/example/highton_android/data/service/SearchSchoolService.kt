package com.example.highton_android.data.service

import com.example.highton_android.data.model.auth.request.SearchSchoolResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchSchoolService {

    @GET("hub/schoolInfo")
    suspend fun searchSchool(
        @Query(value = "SCHUL_NM") schoolName: String,
        @Query(value = "Type") type: String,
        @Query(value = "plndex") index: String,
        @Query(value = "pSize") size: String,
        @Query(value = "KEY") key: String
    ): Response<SearchSchoolResponse>
}