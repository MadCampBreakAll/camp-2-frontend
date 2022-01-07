package com.example.myapplication.api.user

import com.example.myapplication.BuildConfig
import com.example.myapplication.TokenManager
import com.example.myapplication.api.dto.GetMeResponseDto
import okhttp3.OkHttpClient
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class UserApiService {
    val tokenManager: TokenManager;

    constructor(tokenManager: TokenManager){
        this.tokenManager = tokenManager;
    }

    fun getProvider(): UserApiProvider {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(it.request().newBuilder()
                    .addHeader("Authorization", "Bearer" + this.tokenManager.getJWT()).build())
            }.build();

        return Retrofit.Builder()
            .baseUrl(BuildConfig.TEST_BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiProvider::class.java);
    }

}