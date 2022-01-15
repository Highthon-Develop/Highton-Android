package com.example.highton_android.viewmodel

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
            repository.getDiary(token).let { response ->
                if (response.isSuccessful) {
                    _diaryData.value = response.body()?.content
                }

            }
        }

    }

    suspend fun postDiary(body: PostDiaryRequest) {
        viewModelScope.launch {
            repository.postDiary(body).let { response ->
                _isSuccess.value = response.body()?.success

            }
        }

    }
}