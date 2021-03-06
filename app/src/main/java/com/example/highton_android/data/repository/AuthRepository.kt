package com.example.highton_android.data.repository

import com.example.highton_android.data.TokenDataStore
import com.example.highton_android.data.model.auth.request.AccountRequest
import com.example.highton_android.data.model.auth.request.LoginRequest
import com.example.highton_android.data.model.auth.request.SearchSchoolResponse
import com.example.highton_android.data.model.auth.response.AccountResponse
import com.example.highton_android.data.model.auth.response.LoginResponse
import com.example.highton_android.data.service.AuthService
import com.example.highton_android.data.service.SearchSchoolService
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val dataStore: TokenDataStore,
    private val authApi: AuthService,
    private val searchSchoolApi: SearchSchoolService
) {

    var readToken = dataStore.readToken

    suspend fun saveToken(token: String) =
        dataStore.saveToken(token)

    suspend fun postAccount(
        body: AccountRequest
    ): Response<AccountResponse> {
        return authApi.postAccount(body = body)
    }

    suspend fun postLogin(
        body: LoginRequest
    ): Response<LoginResponse> {
        return authApi.postLogin(body = body)
    }

    suspend fun searchSchool(
        schoolName: String,
        type: String,
        index: String,
        size: String,
        key: String
    ): Response<SearchSchoolResponse> {
        return searchSchoolApi.searchSchool(schoolName, type, index, size, key)
    }

}