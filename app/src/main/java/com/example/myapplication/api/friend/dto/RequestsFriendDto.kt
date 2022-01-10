package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class RequestsFriendDto (
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
)