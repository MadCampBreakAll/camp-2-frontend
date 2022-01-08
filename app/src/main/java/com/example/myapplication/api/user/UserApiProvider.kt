package com.example.myapplication.api.user

import com.example.myapplication.api.user.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiProvider {

    @GET("/users/me")
    fun getMe() : Call<GetMeResponseDto>;

    @GET("/friends")
    fun getFriends() : Call<GetMyFriendsResponseDto>;

    @GET("/friends/requests")
    fun getPendingFriends() : Call<GetPendingFriendResponseDto>;

    @POST("/friends/requests")
    fun makeFriend(
        @Body dto: MakeFriendRequestDto
    ) : Call<MakeFriendResponseDto>;

    @POST("/friends/accept")
    fun acceptFriend(
        @Body dto: AcceptFriendRequestDto
    ) : Call<AcceptFriendResponseDto>;
}