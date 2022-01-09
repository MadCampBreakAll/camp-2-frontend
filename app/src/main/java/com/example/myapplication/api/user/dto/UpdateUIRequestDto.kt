package com.example.myapplication.api.user.dto

import com.google.gson.annotations.SerializedName

data class UpdateUIRequestDto (
    @SerializedName("font")
    val font: Int,
    @SerializedName("backgroundColor")
    val backgroundColor: Int
)
