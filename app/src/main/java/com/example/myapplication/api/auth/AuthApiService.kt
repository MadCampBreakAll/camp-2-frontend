package com.example.myapplication.api.auth

import android.content.Context
import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.api.BasicApiService
import com.example.myapplication.api.auth.dto.LoginRequestDto
import com.example.myapplication.api.auth.dto.LoginResponseDto
import com.example.myapplication.api.auth.dto.RegisterRequestDto
import com.example.myapplication.api.auth.dto.RegisterResponseDto
import com.example.myapplication.api.diary.DiaryApiProvider
import com.example.myapplication.util.TokenManager
import com.google.gson.GsonBuilder
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class AuthApiService : BasicApiService{

    private final val apiProvider: AuthApiProvider
    private val tokenManager: TokenManager

    constructor(tokenManager: TokenManager){
        this.tokenManager = tokenManager
        this.apiProvider = getProvider(tokenManager)
    }

    private fun getProvider(tokenManager: TokenManager): AuthApiProvider {
        val httpClient = getBasicHttpClientBuilder().addInterceptor(
            getApiInterceptorWithJWT(tokenManager.getJWT())
        ).build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory
                .create(getBasicGson()))
            .build()
            .create(AuthApiProvider::class.java);
    }


    fun login(
         loginRequestDto: LoginRequestDto,
         success: (LoginResponseDto?) -> Unit,
         fail: ((Throwable) -> Unit)?,
    ) {
        this.apiProvider.login(loginRequestDto).enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(
                call: Call<LoginResponseDto>,
                response: Response<LoginResponseDto>
            ) {
                Log.d("DEBUG", "LOGIN SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                Log.d("DEBUG", "LOGIN FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun register(
        registerRequestDto: RegisterRequestDto,
        success: (RegisterResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?,
    ) {
        this.apiProvider.register(registerRequestDto).enqueue(object: Callback<RegisterResponseDto> {
            override fun onResponse(
                call: Call<RegisterResponseDto>,
                response: Response<RegisterResponseDto>
            ) {
                Log.d("DEBUG", "REGISTER SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<RegisterResponseDto>, t: Throwable) {
                Log.d("DEBUG", "REGISTER FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun loginWithKaKao(
        context: Context,
        callback: (OAuthToken?, Throwable?) -> Unit
    ): Boolean {
        if(tokenManager.hasJWT()){
            return false
        }

        if(UserApiClient.instance.isKakaoTalkLoginAvailable(context)){
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
            return true
        }

        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        return true
    }

}
