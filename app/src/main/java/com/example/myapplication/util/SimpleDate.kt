package com.example.myapplication.util

import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.ui.main.Setting
import java.text.SimpleDateFormat
import java.util.*

class SimpleDate {
    companion object {
        fun of(date: Date) : String {
            val sdf = SimpleDateFormat("yyyy/M/dd hh:mm")
            return sdf.format(date)
        }
    }
}