package com.example.myapplication.api.page.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class PageDto (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("user")
    val user: UserDto?,
    @SerializedName("createdAt")
    val createdAt: LocalDateTime?
)
