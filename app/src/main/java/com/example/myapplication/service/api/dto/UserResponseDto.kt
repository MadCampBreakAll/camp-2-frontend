package com.example.myapplication.service.api.dto

import com.google.gson.annotations.SerializedName

data class UserResponseDto (
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
