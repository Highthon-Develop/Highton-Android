package com.example.highton_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.highton_android.data.FeedPagingDataSource
import com.example.highton_android.data.model.feed.request.PostSchoolEventRequest
import com.example.highton_android.data.model.feed.response.Content
import com.example.highton_android.data.repository.FeedRepository
import com.example.highton_android.data.service.FeedService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: FeedRepository,
    private val api:FeedService
) : ViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess
    suspend fun postSchoolEvent(
        token: String,
        body: PostSchoolEventRequest
    ) {
        viewModelScope.launch {
            repository.postSchoolEvent(token, body).let { response ->
                if (response.isSuccessful) {
                    _isSuccess.value = response.body()?.success
                } else {
                    _isSuccess.value = response.body()?.success
                }
            }

        }
    }

    fun getSchoolEvent(query: String): Flow<PagingData<Content>> {

        return Pager(

            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ) {
            FeedPagingDataSource(
                api,
                query,
            )

        }.flow
            .cachedIn(viewModelScope)
    }


}