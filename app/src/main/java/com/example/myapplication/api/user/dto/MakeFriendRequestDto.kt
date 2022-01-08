package com.example.myapplication.api.user.dto

import com.google.gson.annotations.SerializedName

data class MakeFriendRequestDto (
    @SerializedName("friendId")
    val friendId: String,
)