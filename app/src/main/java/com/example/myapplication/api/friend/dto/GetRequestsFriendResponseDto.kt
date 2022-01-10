package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class GetRequestsFriendResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("pending")
    val pending: List<FriendDto>?,
    @SerializedName("asked")
    val asked: List<FriendDto>?
)