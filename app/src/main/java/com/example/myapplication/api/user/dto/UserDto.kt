package com.example.myapplication.api.user.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Int?,
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
    @SerializedName("font")
    val font: Int?,
    @SerializedName("backgroundColor")
    val backgroundColor: String?,
    @SerializedName("backgroundPaper")
    val backgroundPaper: Int?
)