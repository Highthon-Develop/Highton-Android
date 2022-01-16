package com.example.highton_android.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.highton_android.data.TokenDataStore
import com.example.highton_android.data.model.auth.Token
import com.example.highton_android.data.model.profile.DetailedUser
import com.example.highton_android.data.repository.HomeRepository
import com.example.highton_android.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token> get() = _token

    private val _user: MutableLiveData<NetworkResult<DetailedUser>> = MutableLiveData()
    val user: LiveData<NetworkResult<DetailedUser>> get() = _user

    fun getToken() = viewModelScope.launch {
        tokenDataStore.readToken.collect { token ->
            _token.value = token
        }
    }

    fun getUser() = viewModelScope.launch {
        _user.value = try {
            val response = homeRepository.getProfile(token.value!!.token)

            if (response.isSuccessful && response.body() != null) {
                Log.d("TAG", "getUser: ${response.body().toString()}")
                NetworkResult.Success(response.body()!!.content)
            } else
                NetworkResult.Error(response.message())
        } catch (e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }

}