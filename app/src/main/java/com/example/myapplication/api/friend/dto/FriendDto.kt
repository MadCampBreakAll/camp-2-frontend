package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class FriendDto (
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("body")
    val body: Int,
    @SerializedName("bodyColor")
    val bodyColor: Int,
    @SerializedName("blushColor")
    val blushColor: Int,
    @SerializedName("item")
    val item: Int
)
