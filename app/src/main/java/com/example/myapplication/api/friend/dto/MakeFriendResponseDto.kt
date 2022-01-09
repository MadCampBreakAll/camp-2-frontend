package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class MakeFriendResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("login")
    val login: Boolean?
)