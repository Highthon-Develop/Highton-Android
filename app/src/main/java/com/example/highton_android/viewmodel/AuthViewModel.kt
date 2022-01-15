package com.example.highton_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.highton_android.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var readToken = repository.readToken
    suspend fun saveToken(token: String) {
        repository.saveToken(token)
    }
}