package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class PageDto (
    @SerializedName("id")
    var id: String,
    @SerializedName("userId")
    var userId: String,
    @SerializedName("nextUserId")
    var nextUserId: String,
    @SerializedName("diaryId")
    var diaryId: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String,
    @SerializedName("createdDate")
    var createdDate: LocalDate,
    @SerializedName("color")
    var color: String,
    @SerializedName("image")
    var image: String?
)