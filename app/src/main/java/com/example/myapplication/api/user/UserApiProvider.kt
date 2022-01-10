package com.example.myapplication.api.user

import com.example.myapplication.api.user.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiProvider {

    @GET("/users/me")
    fun getMe() : Call<GetMeResponseDto>

    @POST("/users/avatar")
    fun updateAvatar(@Body dto: UpdateAvatarRequestDto): Call<UpdateAvatarResponseDto>

    @POST("/users/ui")
    fun updateUI(@Body dto: UpdateUIRequestDto): Call<UpdateUIResponseDto>

    @GET("/users")
    fun checkNickname(
        @Query("nickname") nickname:String
    ) : Call<CheckNicknameResponseDto>
}