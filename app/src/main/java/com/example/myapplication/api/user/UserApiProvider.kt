package com.example.myapplication.api.user

import com.example.myapplication.api.user.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiProvider {

    @GET("/users/me")
    fun getMe() : Call<GetMeResponseDto>;


}