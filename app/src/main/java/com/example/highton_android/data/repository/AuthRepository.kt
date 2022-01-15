package com.example.highton_android.data.repository

import com.example.highton_android.data.TokenDataStore
import javax.inject.Inject

class AuthRepository @Inject constructor(private val dataStore: TokenDataStore) {

    var readToken = dataStore.readToken

    suspend fun saveToken(token: String) =
        dataStore.saveToken(token)
}