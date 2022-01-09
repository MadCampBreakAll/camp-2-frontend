package com.example.myapplication.api.auth

import com.example.myapplication.api.auth.dto.LoginRequestDto
import com.example.myapplication.api.auth.dto.LoginResponseDto
import com.example.myapplication.api.auth.dto.RegisterRequestDto
import com.example.myapplication.api.auth.dto.RegisterResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiProvider {
    @POST("users/login")
    fun login(
        @Body loginRequestDto: LoginRequestDto,
    ) : Call<LoginResponseDto>;

    @POST("users/register")
    fun register(
        @Body registerRequestDto: RegisterRequestDto
    ) : Call<RegisterResponseDto>


}