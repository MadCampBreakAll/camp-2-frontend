package com.example.myapplication.service.api

import com.example.myapplication.BuildConfig
import retrofit2.http.POST

interface BasicApiService {
    @POST("${BuildConfig.BASE_URI}/users/login")
    fun login() : String;

}