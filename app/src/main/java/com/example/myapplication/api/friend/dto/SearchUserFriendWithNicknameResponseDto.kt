package com.example.myapplication.api.friend.dto

import com.example.myapplication.api.entity.SearchedFriend
import com.google.gson.annotations.SerializedName

data class SearchUserFriendWithNicknameResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("user")
    val user: SearchedFriend?,
)
