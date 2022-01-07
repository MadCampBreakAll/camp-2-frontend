package com.example.myapplication.service.api

import com.example.myapplication.BuildConfig
import com.example.myapplication.service.api.dto.LoginRequestDto
import com.example.myapplication.service.api.dto.RegisterRequestDto
import com.example.myapplication.service.api.dto.RegisterResponseDto
import com.example.myapplication.service.api.dto.UserResponseDto
import retrofit2.Call
import retrofit2.http.*

interface BasicApiProvider {
    @Headers("accept: application/json",
        "content-type: application/json")
    @POST("users/login")
    fun login(
        @Body loginRequestDto: LoginRequestDto,
    ) : Call<UserResponseDto>;

    fun register(
        @Body registerRequestDto: RegisterRequestDto
    ) : Call<RegisterResponseDto>

    @GET("/")
    fun test() : Call<Unit>

}