package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("body")
    val body: Int?,
    @SerializedName("bodyColor")
    val bodyColor: Int?,
    @SerializedName("blushColor")
    val blush_color: Int?,
    @SerializedName("item")
    val item: Int?,
    @SerializedName("font")
    val font: String?,
)