package com.example.myapplication.api.page.dto

import com.google.gson.annotations.SerializedName

data class GetDiaryInnerPagesResponse (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("pages")
    val pages: List<PageDto>
)
