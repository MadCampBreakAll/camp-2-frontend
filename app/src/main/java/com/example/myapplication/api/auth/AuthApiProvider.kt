package com.example.myapplication.api.auth

import com.example.myapplication.api.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
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

    @GET("/")
    fun test() : Call<Unit>
}