package com.example.myapplication.api.diary.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class DiaryDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("nextUser")
    val nextUser: NextUserDto,
    @SerializedName("chamyeoUsers")
    val chamyeoUsers: List<ChamyeoUserDto>,
)
