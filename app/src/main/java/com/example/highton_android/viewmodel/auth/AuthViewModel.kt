package com.example.highton_android.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.highton_android.data.model.auth.request.AccountRequest
import com.example.highton_android.data.model.auth.request.LoginRequest
import com.example.highton_android.data.model.auth.request.Row
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

    private val _scCode = MutableLiveData<String>()
    val scCode: LiveData<String> get() = _scCode

    private val _schulCode = MutableLiveData<String>()
    val schulCode: LiveData<String> get() = _schulCode
    private val _schoolName = MutableLiveData<String>()
    val schoolName: LiveData<String> get() = _schoolName
    private val _schoolSearch = MutableLiveData<List<Row>?>()
    val schoolSearch: LiveData<List<Row>?> get() = _schoolSearch
    private suspend fun saveToken(token: String) {
        repository.saveToken(token)
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

    suspend fun searchSchool(
        schoolName: String,
    ) = viewModelScope.launch {
        repository.searchSchool(
            schoolName,
            "json",
            "1",
            "100",
            ""
        ).let { response ->
            if (response.isSuccessful) {
                response.body()?.schoolInfo?.map { data ->
                    data.row?.filter {
                        it.sCHULNM?.contains("고등학교") == true
                    }.apply {
                        _schoolSearch.value = this
                    }
                }
            }

        }

    }

    fun getSchoolName(name: String, scCode: String, schul_code: String) {

        _schoolName.value = name
        _scCode.value = scCode
        _schulCode.value = schul_code
    }

}

