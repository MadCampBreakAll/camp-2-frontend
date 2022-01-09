package com.example.myapplication.api.diary.dto

import com.google.gson.annotations.SerializedName

data class NextUserDto (
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
    @SerializedName("item")
    val item: Int?
)
