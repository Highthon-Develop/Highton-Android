package com.example.highton_android.data.model.profile


import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("idx")
    val idx: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("ATPT_OFCDC_SC_CODE")
    val sidoCode: String,
    @SerializedName("SD_SCHUL_CODE")
    val schoolCode: String
)