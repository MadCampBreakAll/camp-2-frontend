package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName

data class Diary (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("nextUser")
    val nextUser: NextUser,
    @SerializedName("chamyeoUsers")
    val chamyeoUsers: List<ChamyeoUser>
)
