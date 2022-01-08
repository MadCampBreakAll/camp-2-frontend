package com.example.myapplication.api.user.dto

import com.example.myapplication.api.entity.PendingFriends
import com.google.gson.annotations.SerializedName

data class GetPendingFriendResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("users")
    val users: List<PendingFriends>?
)