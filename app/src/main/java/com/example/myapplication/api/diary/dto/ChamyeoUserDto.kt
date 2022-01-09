package com.example.myapplication.api.diary.dto

import com.google.gson.annotations.SerializedName

data class ChamyeoUserDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("body")
    val body: Int?,
    @SerializedName("bodyColor")
    val bodyColor: Int?,
    @SerializedName("blushColor")
    val blushColor: Int?,
    @SerializedName("item")
    val item: Int?,
)