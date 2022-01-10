package com.example.myapplication.api.friend

import com.example.myapplication.api.friend.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FriendApiProvider {
    @GET("/friends")
    fun getFriends() : Call<GetMyFriendsResponseDto>

    @GET("/friends/requests")
    fun getPendingFriends() : Call<GetRequestsFriendResponseDto>

    @POST("/friends/requests")
    fun makeFriend(
        @Body dto: MakeFriendRequestDto
    ) : Call<MakeFriendResponseDto>

    @POST("/friends/accept")
    fun acceptFriend(
        @Body dto: AcceptFriendRequestDto
    ) : Call<AcceptFriendResponseDto>

    @POST("/friends/search")
    fun searchFriendWithNickname(
        @Body dto: SearchUserFriendWithNicknameRequestDto,
    ) : Call<SearchUserFriendWithNicknameResponseDto>
}