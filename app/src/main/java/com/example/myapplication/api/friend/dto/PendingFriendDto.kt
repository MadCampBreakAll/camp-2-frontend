package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class PendingFriendDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("nickname")
    val nickname: String,
)