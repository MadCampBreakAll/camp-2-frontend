package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class AcceptFriendRequestDto (
       @SerializedName("friendId")
       val friendId: String,
       @SerializedName("accept")
       val accept: Boolean,
)