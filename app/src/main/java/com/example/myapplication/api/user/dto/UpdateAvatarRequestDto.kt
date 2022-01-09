package com.example.myapplication.api.user.dto

import com.google.gson.annotations.SerializedName

data class UpdateAvatarRequestDto (
    @SerializedName("body")
    val body: Int,
    @SerializedName("bodyColor")
    val bodyColor: Int,
    @SerializedName("blushColor")
    val blushColor: Int,
    @SerializedName("item")
    val item: Int
)
