package com.example.highton_android.data.service

import com.example.highton_android.data.model.auth.request.AccountRequest
import com.example.highton_android.data.model.auth.request.LoginRequest
import com.example.highton_android.data.model.auth.response.AccountResponse
import com.example.highton_android.data.model.auth.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {


    @POST("account")
    suspend fun postAccount(
        @Body body: AccountRequest
    ): Response<AccountResponse>

    @POST("account/login")
    suspend fun postLogin(
        @Body body: LoginRequest
    ): Response<LoginResponse>
}