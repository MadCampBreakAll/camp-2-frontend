package com.example.myapplication.api.page.dto

import com.example.myapplication.api.diary.dto.DiaryDto
import com.google.gson.annotations.SerializedName

data class GetDiaryInnerPagesResponse (
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("pages")
    val pages: List<PageDto>,
    @SerializedName("diary")
    val diary: DiaryDto
)
