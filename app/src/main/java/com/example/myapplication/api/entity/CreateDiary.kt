package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName

data class CreateDiary (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("nextUserId")
    val nextUserId: String?,
    @SerializedName("ownerId")
    val ownerId: String?,
)
