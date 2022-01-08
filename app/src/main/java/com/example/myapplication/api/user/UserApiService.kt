package com.example.myapplication.api.user

import com.example.myapplication.BuildConfig
import com.example.myapplication.util.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class UserApiService {
    val tokenManager: TokenManager;

    constructor(tokenManager: TokenManager){
        this.tokenManager = tokenManager;
    }

    fun getProvider(): UserApiProvider {

        val apiInterceptor = Interceptor {
            val originalRequest = it.request()
            val newHttp = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + this.tokenManager.getJWT())
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

}