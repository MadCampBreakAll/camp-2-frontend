package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class PageDto (
    @SerializedName("id")
    var id: String,
    @SerializedName("userId")
    var userId: String,
    @SerializedName("diaryId")
    var diaryId: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: Int,
    @SerializedName("createdAt")
    var createdAt: LocalDateTime,
    @SerializedName("color")
    var color: String,
    @SerializedName("image")
    var image: String?
)