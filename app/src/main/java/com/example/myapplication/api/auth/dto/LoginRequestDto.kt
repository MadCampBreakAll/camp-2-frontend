package com.example.myapplication.api.auth.dto

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("accessToken")
    val accessToken: String,
)