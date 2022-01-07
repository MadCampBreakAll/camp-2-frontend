package com.example.myapplication

import java.time.LocalDate

data class DiaryItem (
    var title: String,
    var createdDate: LocalDate,
    var nextWriter: String,
    var pageItem: String,
)