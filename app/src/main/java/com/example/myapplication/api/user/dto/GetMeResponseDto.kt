package com.example.myapplication.api.user.dto

import com.google.gson.annotations.SerializedName

data class GetMeResponseDto(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("user")
    val user: UserDto?,
    @SerializedName("login")
    val login: Boolean?
)