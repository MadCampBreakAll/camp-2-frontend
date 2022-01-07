package com.example.myapplication.api.user

import com.example.myapplication.api.dto.GetMeResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface UserApiProvider {
    @GET("/users/me")
    fun getMe() : Call<GetMeResponseDto>;
    fun getFriends();
    fun inviteFriend();
    fun invitedByFriend();
    fun addFriend();
}