package com.example.highton_android.data.model.auth.request

import com.google.gson.annotations.SerializedName

data class SchoolInfo(
    @SerializedName("head")
    val head: List<Head>?,
    @SerializedName("row")
    val row: List<Row>?
)

data class SearchSchoolResponse(
    @SerializedName("schoolInfo")
    val schoolInfo: List<SchoolInfo>
)

data class Head(

    @SerializedName("list_total_count")
    val listTotalCount: Int?,

    @SerializedName("RESULT")
    val rESULT: RESULT?
)

data class RESULT(
    @SerializedName("CODE")
    val cODE: String?,
    @SerializedName("MESSAGE")
    val mESSAGE: String?
)

