package com.example.myapplication.api.entity

import com.google.gson.annotations.SerializedName

data class ChamyeoUser (
    @SerializedName("id")
    val id: Long,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("body")
    val bodyColor: Int?,
    @SerializedName("blushColor")
    val blushColor: Int?,
    @SerializedName("item")
    val item: Int?,
)