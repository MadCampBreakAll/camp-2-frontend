package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String?,
    @SerializedName("bodyColor")
    val body_color: String?,
    @SerializedName("blushColor")
    val blush_color: String?,
    @SerializedName("item")
    val item: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("font")
    val font: String?,
    @SerializedName("status")
    val status: Boolean?,
)