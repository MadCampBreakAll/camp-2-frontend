package com.example.myapplication.api.dto

import java.time.LocalDate

data class DiaryDto (
    var title: String,
    var createdDate: LocalDate,
    var nextWriter: String,
    var pageItem: String,
)