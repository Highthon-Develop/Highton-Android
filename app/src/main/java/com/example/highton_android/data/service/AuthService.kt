package com.example.highton_android.data.service

import com.example.highton_android.data.model.auth.request.AccountRequest
import com.example.highton_android.data.model.auth.request.LoginRequest
import com.example.highton_android.data.model.auth.response.AccountResponse
import com.example.highton_android.data.model.auth.response.LoginResponse
import retrofit2.Response

interface AuthService {


    suspend fun postAccount(
        body : AccountRequest
    ):Response<AccountResponse>

    suspend fun postLogin(
        body : LoginRequest
    ):Response<LoginResponse>
}