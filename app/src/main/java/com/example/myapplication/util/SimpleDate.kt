package com.example.myapplication.util

import android.os.Build
import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.ui.main.Setting
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class SimpleDate {
    companion object {

        val defaultPattern = "yyyy/M/dd a hh:mm"

        val sdf = SimpleDateFormat(defaultPattern)
        fun of(date: Date) : String {
            return sdf.format(date)
        }

        fun getUTCTime(date: Date): String{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                var localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                localDateTime = localDateTime.plusHours(9)
                return localDateTime.format(DateTimeFormatter.ofPattern(defaultPattern))
            };
            return sdf.format(date)
        }
    }
}