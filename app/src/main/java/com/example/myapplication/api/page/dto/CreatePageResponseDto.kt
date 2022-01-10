package com.example.myapplication.api.page.dto

import com.google.gson.annotations.SerializedName

data class CreatePageResponseDto (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("page")
    val page: CreatePageDto?,
    @SerializedName("login")
    val login: Boolean?,

    )
