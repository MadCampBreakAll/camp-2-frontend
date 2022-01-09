package com.example.myapplication.api.user.dto

import com.google.gson.annotations.SerializedName

data class UpdateUIResponseDto (
    @SerializedName("status")
    val status: Boolean?
)
