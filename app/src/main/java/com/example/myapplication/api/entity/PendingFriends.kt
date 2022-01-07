package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName

data class PendingFriends (
    @SerializedName("id")
    val id: String,
    @SerializedName("nickname")
    val nickname: String,
)