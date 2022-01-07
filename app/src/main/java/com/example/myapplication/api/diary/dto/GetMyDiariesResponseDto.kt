package com.example.myapplication.api.diary.dto

import com.example.myapplication.api.entity.Diary
import com.google.gson.annotations.SerializedName

data class GetMyDiariesResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("diaries")
    val diaries: List<Diary>?,
)