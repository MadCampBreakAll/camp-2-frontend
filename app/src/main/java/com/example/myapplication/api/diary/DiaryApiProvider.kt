package com.example.myapplication.api.diary

interface DiaryApiProvider {
    fun getDiaries();
    fun addDiary();
    fun getDiary();
}