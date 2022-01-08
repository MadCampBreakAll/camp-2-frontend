package com.example.myapplication.api.page.dto

import com.google.gson.annotations.SerializedName

data class CreatePageRequestDto (
    @SerializedName("diaryId")
    val diaryId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("color")
    val color: String,
)
