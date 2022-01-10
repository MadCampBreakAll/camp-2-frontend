package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class AcceptFriendRequestDto (
       @SerializedName("friendId")
       val friendId: Int,
       @SerializedName("accept")
       val accept: Boolean,
)