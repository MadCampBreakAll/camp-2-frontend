package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName

data class GetMeResponseDto (
    @SerializedName("id")
    val id: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("body")
    val body: Int?,
    @SerializedName("bodyColor")
    val bodyColor: Int?,
    @SerializedName("blushColor")
    val blushColor: Int?,
    @SerializedName("font")
    val font: Int?,
    @SerializedName("item")
    val item: Int?,
    @SerializedName("status")
    val status: Boolean?,
)