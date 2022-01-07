package com.example.myapplication.api.user

import com.example.myapplication.api.dto.GetMeResponseDto
import com.example.myapplication.api.dto.GetMyFriendsResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiProvider {
    @GET("/users/me")
    fun getMe() : Call<GetMeResponseDto>;
    @GET("/friends")
    fun getFriends() : Call<GetMyFriendsResponseDto>;
    fun inviteFriend();
    @GET("/friends/requests")
    fun getFendingFriends();
    @POST("/friends/requests")
    fun makeFriend();
}