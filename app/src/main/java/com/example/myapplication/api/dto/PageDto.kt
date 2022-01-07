package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName
import org.w3c.dom.Text
import java.time.LocalDate

data class PageDto (
    @SerializedName("id")
    var id: Long,
    @SerializedName("userId")
    var userId: Long,
    @SerializedName("nextUserId")
    var nextUserId: Long,
    @SerializedName("diaryId")
    var diaryId: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: Text,
    @SerializedName("createdDate")
    var createdDate: LocalDate,
    @SerializedName("color")
    var color: String,
    @SerializedName("image")
    var image: String?
    )