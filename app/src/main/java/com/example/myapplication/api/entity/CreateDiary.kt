package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class CreateDiary(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("createdAt")
    val createdAt: LocalDateTime?,
    @SerializedName("nextUserId")
    val nextUserId: String?,
    @SerializedName("ownerId")
    val ownerId: String?,
)
