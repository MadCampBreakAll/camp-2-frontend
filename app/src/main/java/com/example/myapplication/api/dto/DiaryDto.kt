package com.example.myapplication.api.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class DiaryDto (
    @SerializedName("id")
    var id: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("createdAt")
    var createdAt: LocalDate,
//    @SerializedName("nextWriter")
    var nextWriter: GetMeResponseDto,
//    @SerializedName("page")
    var page: PageDto?,
)