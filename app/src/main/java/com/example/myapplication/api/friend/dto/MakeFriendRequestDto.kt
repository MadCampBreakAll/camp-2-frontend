package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class MakeFriendRequestDto (
    @SerializedName("friendId")
    val friendId: Int,
)