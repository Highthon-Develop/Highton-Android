package com.example.highton_android.data.model.auth.request

import com.google.gson.annotations.SerializedName

data class AccountRequest(
    val email: String,
    val password: String,
    val name: String,
    val birthYear: Int,
    val birthMonth: Int,
    val birthDay: Int,
    @SerializedName("ATPT_OFCDC_SC_CODE") val scCode: String,
    @SerializedName("SD_SCHUL_CODE") val schulCode: String,
    val grade: Int,
    val sex: String,
    @SerializedName("nickname") val nickName: String


)