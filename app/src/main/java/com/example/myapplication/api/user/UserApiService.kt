package com.example.myapplication.api.user

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.api.BasicApiService
import com.example.myapplication.api.user.dto.*
import com.example.myapplication.util.TokenManager
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class UserApiService : BasicApiService{
    private val userApiProvider: UserApiProvider;

    constructor(tokenManager: TokenManager){
        this.userApiProvider = getProvider(tokenManager);
    }

    private fun getProvider(tokenManager: TokenManager): UserApiProvider {
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

    fun updateAvatar(
        dto: UpdateAvatarRequestDto,
        success: (UpdateAvatarResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.userApiProvider.updateAvatar(dto).enqueue(object: Callback<UpdateAvatarResponseDto>{
            override fun onResponse(
                call: Call<UpdateAvatarResponseDto>,
                response: Response<UpdateAvatarResponseDto>
            ) {
                Log.d("DEBUG", "UPDATE AVATAR SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<UpdateAvatarResponseDto>, t: Throwable) {
                Log.d("DEBUG", "UPDATE AVATAR FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun updateUI(
        dto: UpdateUIRequestDto,
        success: (UpdateUIResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.userApiProvider.updateUI(dto).enqueue(object: Callback<UpdateUIResponseDto>{
            override fun onResponse(
                call: Call<UpdateUIResponseDto>,
                response: Response<UpdateUIResponseDto>
            ) {
                Log.d("DEBUG", "UPDATE UI SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<UpdateUIResponseDto>, t: Throwable) {
                Log.d("DEBUG", "UPDATE UI FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun checkNickname(
        nickname: String,
        success: (CheckNicknameResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ) {
        this.userApiProvider.checkNickname(nickname).enqueue(object: Callback<CheckNicknameResponseDto>{
            override fun onResponse(
                call: Call<CheckNicknameResponseDto>,
                response: Response<CheckNicknameResponseDto>
            ) {
                Log.d("DEBUG", "CHECK NICKNAME SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body())
            }

            override fun onFailure(call: Call<CheckNicknameResponseDto>, t: Throwable) {
                Log.d("DEBUG", "CHECK NICKNAME FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t)
            }
        })
    }

}