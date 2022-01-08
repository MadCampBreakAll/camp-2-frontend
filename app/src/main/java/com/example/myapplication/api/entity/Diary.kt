package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Diary (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("createdAt")
    val createdAt: LocalDateTime,
    @SerializedName("nextUser")
    val nextUser: NextUser,
    @SerializedName("chamyeoUsers")
    val chamyeoUsers: List<ChamyeoUser>
)
