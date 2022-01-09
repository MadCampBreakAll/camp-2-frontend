package com.example.myapplication.api.friend

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.api.BasicApiService
import com.example.myapplication.api.friend.dto.*
import com.example.myapplication.util.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FriendApiService : BasicApiService{

    private var friendApiProvider: FriendApiProvider

    constructor(tokenManager: TokenManager){
        friendApiProvider = getProvider(tokenManager)
    }

    private fun getProvider(tokenManager: TokenManager) : FriendApiProvider{
        val httpClient = getBasicHttpClientBuilder().addInterceptor(
            getApiInterceptorWithJWT(tokenManager.getJWT())
        ).build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(
                getBasicGson()
            ))
            .build()
            .create(FriendApiProvider::class.java)
    }


    fun getFriends(
        success: (GetMyFriendsResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.friendApiProvider.getFriends().enqueue(object: Callback<GetMyFriendsResponseDto> {
            override fun onResponse(
                call: Call<GetMyFriendsResponseDto>,
                response: Response<GetMyFriendsResponseDto>
            ) {
                Log.d("DEBUG", "GET MY FRIEND SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<GetMyFriendsResponseDto>, t: Throwable) {
                Log.d("DEBUG", "GET MY FRIEND FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun getPendingFriend(
        success: (GetPendingFriendResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.friendApiProvider.getPendingFriends().enqueue(object:
            Callback<GetPendingFriendResponseDto> {
            override fun onResponse(
                call: Call<GetPendingFriendResponseDto>,
                response: Response<GetPendingFriendResponseDto>
            ) {
                Log.d("DEBUG", "GET MY PENDING FRIEND SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<GetPendingFriendResponseDto>, t: Throwable) {
                Log.d("DEBUG", "GET MY PENDING FRIEND FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun makeFriend(
        dto: MakeFriendRequestDto,
        success: (MakeFriendResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.friendApiProvider.makeFriend(dto).enqueue(object: Callback<MakeFriendResponseDto> {
            override fun onResponse(
                call: Call<MakeFriendResponseDto>,
                response: Response<MakeFriendResponseDto>
            ) {
                Log.d("DEBUG", "MAKE FRIEND SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<MakeFriendResponseDto>, t: Throwable) {
                Log.d("DEBUG", "MAKE PENDING FRIEND FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun acceptFriend(
        dto: AcceptFriendRequestDto,
        success: (AcceptFriendResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.friendApiProvider.acceptFriend(dto).enqueue(object: Callback<AcceptFriendResponseDto> {
            override fun onResponse(
                call: Call<AcceptFriendResponseDto>,
                response: Response<AcceptFriendResponseDto>
            ) {
                Log.d("DEBUG", "ACCEPT FRIEND SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<AcceptFriendResponseDto>, t: Throwable) {
                Log.d("DEBUG", "ACCEPT FRIEND FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun searchUserFriendWithNickname(
        dto: SearchUserFriendWithNicknameRequestDto,
        success: (SearchUserFriendWithNicknameResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.friendApiProvider.searchFriendWithNickname(dto).enqueue(object: Callback<SearchUserFriendWithNicknameResponseDto> {
            override fun onResponse(
                call: Call<SearchUserFriendWithNicknameResponseDto>,
                response: Response<SearchUserFriendWithNicknameResponseDto>
            ) {
                Log.d("DEBUG", "SEARCH USER SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(
                call: Call<SearchUserFriendWithNicknameResponseDto>,
                t: Throwable
            ) {
                Log.d("DEBUG", "SEARCH USER FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

}