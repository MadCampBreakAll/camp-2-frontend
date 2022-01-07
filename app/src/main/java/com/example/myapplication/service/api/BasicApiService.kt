package com.example.myapplication.service.api

import com.example.myapplication.BuildConfig
import com.example.myapplication.service.api.dto.RegisterRequestDto
import com.example.myapplication.service.api.dto.RegisterResponseDto
import com.example.myapplication.service.api.dto.UserResponseDto
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BasicApiService {

    companion object{
        val Call = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BasicApiProvider::class.java);
    }

}