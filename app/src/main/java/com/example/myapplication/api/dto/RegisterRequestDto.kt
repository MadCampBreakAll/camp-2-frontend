package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto (
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("body")
    val body: Int,
    @SerializedName("bodyColor")
    val bodyColor: Int,
    @SerializedName("blushColor")
    val blushColor: Int,
    @SerializedName("font")
    val font: Int,
    @SerializedName("item")
    val item: Int,
)