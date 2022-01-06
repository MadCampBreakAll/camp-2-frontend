package com.example.myapplication.service.api

import com.example.myapplication.BuildConfig
import com.example.myapplication.service.api.dto.UserResponseDto
import retrofit2.Call
import retrofit2.http.*

interface BasicApiProvider {
    @Headers("accept: application/json",
        "content-type: application/json")
    @POST("users/login")
    fun login(
        @Body params: HashMap<String, String>,
    ) : Call<UserResponseDto>;

    @GET("/")
    fun test() : Call<Unit>

}