package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName

data class Friend (
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("body")
    val body: Int,
    @SerializedName("bodyColor")
    val bodyColor: String,
    @SerializedName("blushColor")
    val blushColor: String,
    @SerializedName("item")
    val item: Int
)
