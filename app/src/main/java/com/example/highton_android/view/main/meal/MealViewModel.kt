package com.example.highton_android.view.main.meal

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.highton_android.data.TokenDataStore
import com.example.highton_android.data.model.profile.DetailedUser
import com.example.highton_android.data.repository.HomeRepository
import com.example.highton_android.data.repository.MealRepository
import com.example.highton_android.utils.NetworkResult
import com.example.mealgo.data.meal.model.MealResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    application: Application,
    private val mealRepository: MealRepository,
    private val homeRepository: HomeRepository,
    val tokenDataStore: TokenDataStore
) : AndroidViewModel(application) {
    private val _mealResult: MutableLiveData<NetworkResult<String>> =
        MutableLiveData(NetworkResult.Loading())
    val mealResult: LiveData<NetworkResult<String>> get() = _mealResult

    private val _user: MutableLiveData<DetailedUser> = MutableLiveData()
    val user: LiveData<DetailedUser> get() = _user

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMeal() = viewModelScope.launch {
        val queries = HashMap<String, String>()
        queries["KEY"] = "2184fd7c40cc46d19af258829cb14159"
        queries["Type"] = "json"
        queries["ATPT_OFCDC_SC_CODE"] = _user.value!!.school.sidoCode
        queries["SD_SCHUL_CODE"] = _user.value!!.school.schoolCode
        queries["MLSV_YMD"] = "20211223"
            //LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        _mealResult.value = try {
            val response = mealRepository.getMeal(queries)

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(processMeal(response.body()!!))
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }

    private fun processMeal(body: MealResponse): String =
        body.mealServiceDietInfo[1].mealList[0].dishName
            .replace("<br/>", "\n")
            .replace(Regex("[^ㄱ-ㅎㅏ-ㅣ가-힣-\n]"), "")


    fun getUser(token: String) = viewModelScope.launch {
        _user.value = try {
            val response = homeRepository.getProfile(token)

            if (response.isSuccessful && response.body()!!.success)
                response.body()!!.content
            else
                null
        } catch (e: Exception) {
            null
        }
    }

    var readToken = tokenDataStore.readToken
}