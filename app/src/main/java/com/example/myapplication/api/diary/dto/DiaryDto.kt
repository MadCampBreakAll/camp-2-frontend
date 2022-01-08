package com.example.myapplication.api.diary.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class DiaryDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("createdAt")
    val createdAt: LocalDateTime,
    @SerializedName("nextUser")
    val nextUser: NextUserDto,
    @SerializedName("chamyeoUsers")
    val chamyeoUsers: List<ChamyeoUserDto>
)
