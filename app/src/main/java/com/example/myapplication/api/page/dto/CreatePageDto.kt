package com.example.myapplication.api.page.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class CreatePageDto (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("diaryId")
    val diaryId: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("createdAt")
    val createdAt: LocalDateTime?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("img")
    val img: String?,
)
