package com.example.myapplication.api.dto

import com.example.myapplication.api.entity.Friend
import com.google.gson.annotations.SerializedName

data class GetMyFriendsResponseDto (
    @SerializedName("users")
    val friends: List<Friend>
)
