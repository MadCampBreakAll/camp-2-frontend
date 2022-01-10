package com.example.myapplication.ui.friend

import com.google.gson.annotations.SerializedName

data class RequestsFriend (
    val id: Int,
    val nickname: String,
    val body: Int,
    val bodyColor: Int,
    val blushColor: Int,
    val item: Int,
    val pending: Boolean,
)