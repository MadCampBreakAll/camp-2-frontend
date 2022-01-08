package com.example.myapplication.api.user.dto

import com.example.myapplication.api.entity.User
import com.google.gson.annotations.SerializedName

data class GetMeResponseDto(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("user")
    val user: User?
)