package com.example.myapplication.api.friend.dto

import com.example.myapplication.api.friend.dto.PendingFriendDto
import com.google.gson.annotations.SerializedName

data class GetPendingFriendResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("users")
    val users: List<PendingFriendDto>?
)