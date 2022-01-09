package com.example.myapplication.api.friend.dto

import com.example.myapplication.api.friend.dto.FriendDto
import com.google.gson.annotations.SerializedName

data class GetMyFriendsResponseDto (
    @SerializedName("users")
    val friends: List<FriendDto>?
)
