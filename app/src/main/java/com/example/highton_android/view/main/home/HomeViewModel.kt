package com.example.highton_android.view.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.highton_android.data.model.recommendation.RecommendationResponse
import com.example.highton_android.data.repository.RecommendationRepository
import com.example.highton_android.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendationRepository: RecommendationRepository
) : ViewModel() {
    private val _recommendationResult : MutableLiveData<NetworkResult<RecommendationResponse>> = MutableLiveData(NetworkResult.Loading())
    val recommendationResult : LiveData<NetworkResult<RecommendationResponse>> get() = _recommendationResult

    fun getRecommendation() = viewModelScope.launch {
        _recommendationResult.value = try {
            val response = recommendationRepository.getRecommendation()

            if (response.isSuccessful && response.body() != null)
                NetworkResult.Success(response.body()!!)
            else
                NetworkResult.Error(response.message())
        } catch (e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }
}