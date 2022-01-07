package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName

data class GetMeResponseDto (
    @SerializedName("id")
    val id: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("bodyColor")
    val bodyColor: String?,
    @SerializedName("blushColor")
    val blushColor: String?,
    @SerializedName("font")
    val font: String?,
    @SerializedName("item")
    val item: String?,
)