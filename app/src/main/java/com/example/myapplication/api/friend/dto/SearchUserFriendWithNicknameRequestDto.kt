package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class SearchUserFriendWithNicknameRequestDto(
    @SerializedName("nickname")
    val nickname: String,
)
