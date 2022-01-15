package com.example.highton_android.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.highton_android.data.model.auth.request.AccountRequest
import com.example.highton_android.data.model.auth.request.LoginRequest
import com.example.highton_android.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var readToken = repository.readToken
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> get() = _isFailure

    private val _birthDay = MutableLiveData<String>()
    val birthDay: LiveData<String> get() = _birthDay

    private val _grade = MutableLiveData<String>()
    val grade: LiveData<String> get() = _grade

    private val _sex = MutableLiveData<String>()
    val sex: LiveData<String> get() = _sex

    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private suspend fun saveToken(token: String) {
        repository.saveToken(token)
    }

    init {
        _grade.value = "1학년"
        _sex.value = "남성"
    }

    suspend fun postAccount(body: AccountRequest) = viewModelScope.launch {

        repository.postAccount(body).let { response ->
            if (response.isSuccessful) {
                _isSuccess.value = response.body()?.success
            } else {
                _isFailure.value = response.message()
            }
        }
    }

    suspend fun postLogin(body: LoginRequest) = viewModelScope.launch {
        repository.postLogin(body).let { response ->
            if (response.isSuccessful) {
                _isSuccess.value = response.body()?.success
                saveToken(response.body()!!.token)
            } else {
                _isFailure.value = response.message()
            }
        }



    }
    fun getBirthDay(data: String) {
        _birthDay.value = data
    }

    fun getGrade(data: String) {
        _grade.value = data
    }

    fun getSex(data: String) {
        _sex.value = data
    }

    fun getNickName(data: String) {
        _nickName.value = data
    }

    fun getEmail(data: String) {
        _email.value=data

    }
}