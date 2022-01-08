package com.example.myapplication.api.user

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.api.user.dto.*
import com.example.myapplication.util.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class UserApiService {
    private val userApiProvider: UserApiProvider;

    constructor(tokenManager: TokenManager){
        this.userApiProvider = getProvider(tokenManager);
    }

    private fun getProvider(tokenManager: TokenManager): UserApiProvider {

        val apiInterceptor = Interceptor {
            val originalRequest = it.request()
            val newHttp = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + tokenManager.getJWT())
                .build()
            it.proceed(newHttp)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .build();

        return Retrofit.Builder()
            .baseUrl(BuildConfig.TEST_BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiProvider::class.java);
    }

    fun getMe(
        success: (GetMeResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.userApiProvider.getMe().enqueue(object: Callback<GetMeResponseDto>{
            override fun onResponse(
                call: Call<GetMeResponseDto>,
                response: Response<GetMeResponseDto>
            ) {
                Log.d("DEBUG", "GET ME SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<GetMeResponseDto>, t: Throwable) {
                Log.d("DEBUG", "GET ME FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun getFriends(
        success: (GetMyFriendsResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.userApiProvider.getFriends().enqueue(object: Callback<GetMyFriendsResponseDto>{
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
        this.userApiProvider.getPendingFriends().enqueue(object: Callback<GetPendingFriendResponseDto>{
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
        this.userApiProvider.makeFriend(dto).enqueue(object: Callback<MakeFriendResponseDto>{
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
        this.userApiProvider.acceptFriend(dto).enqueue(object: Callback<AcceptFriendResponseDto>{
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
                Log.d("DEBUG", "MAKE PENDING FRIEND FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }



}