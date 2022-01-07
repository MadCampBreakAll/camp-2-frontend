package com.example.myapplication.api.auth

import com.example.myapplication.BuildConfig
import com.example.myapplication.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthApiService {

    val tokenManager: TokenManager;

    constructor(tokenManager: TokenManager){
        this.tokenManager = tokenManager;
    }

    fun getProvider(): AuthApiProvider {
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
            .create(AuthApiProvider::class.java);
    }

}