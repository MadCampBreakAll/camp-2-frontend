package com.example.myapplication.api.diary.dto

import com.google.gson.annotations.SerializedName

data class CreateDiaryRequestDto (
    @SerializedName("title")
    val title: String,
    @SerializedName("userIds")
    val userIds: List<Int>
)
