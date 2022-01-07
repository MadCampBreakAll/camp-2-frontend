package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName

data class MakeFriendRequestDto (
    @SerializedName("friendId")
    val friendId: Int
)