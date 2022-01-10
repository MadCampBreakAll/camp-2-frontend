package com.example.myapplication.api.user.dto

import com.google.gson.annotations.SerializedName

data class CheckNicknameResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("login")
    val login: Boolean?

)
