package com.example.myapplication.api.dto

data class RegisterRequestDto (
    val accessToken: String,
    val nickname: String,
    val body: Int,
    val bodyColor: String,
    val blushColor: String,
    val font: String,
    val item: Int,
)