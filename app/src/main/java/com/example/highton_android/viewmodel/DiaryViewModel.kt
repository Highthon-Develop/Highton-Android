package com.example.highton_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.highton_android.data.model.diary.PostDiaryRequest
import com.example.highton_android.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val repository: DiaryRepository
) : ViewModel() {

    private val _diaryData = MutableLiveData<List<String>>()
    val diaryData = _diaryData

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess = _isSuccess

    suspend fun getDiary(token: String) {
        viewModelScope.launch {
            try {
                repository.getDiary(token).let { response ->
                    Log.d("TAG", "getDiary: ${response}")
                    if (response.isSuccessful) {
                        _diaryData.value = response.body()?.content?.map { it.content }
                    } else {
                        Log.d("TAG", "getDiary: ${response.message()} ")
                    }

                }
            } catch (e: Exception) {
                Log.d("TAG", "getDiary: ${e.message} ")

            }
        }

    }

    suspend fun postDiary(token: String, body: PostDiaryRequest) {
        viewModelScope.launch {
            repository.postDiary(token, body).let { response ->

                _isSuccess.value = response.isSuccessful

            }
        }

    }
}