package com.example.myapplication.api.diary.dto

import com.google.gson.annotations.SerializedName

data class CreateDiaryResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("diary")
    val diary: CreateDiaryDto?,
)
