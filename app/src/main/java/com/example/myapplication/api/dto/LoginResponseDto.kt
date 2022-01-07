package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName

data class LoginResponseDto (
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("token")
    val token: String,
)