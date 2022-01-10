package com.example.myapplication.api.friend.dto

import com.google.gson.annotations.SerializedName

data class SearchedFriendDto (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("nickname")
    val nickname: String?,
)
