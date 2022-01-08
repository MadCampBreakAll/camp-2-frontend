package com.example.myapplication.api.auth

import com.example.myapplication.BuildConfig
import com.example.myapplication.util.TokenManager
import com.example.myapplication.api.diary.DiaryApiProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiaryApiService {

    val tokenManager: TokenManager;

    constructor(tokenManager: TokenManager){
        this.tokenManager = tokenManager;
    }

    fun getProvider(): DiaryApiProvider{
        val api_interceptor = Interceptor {
            val originalRequest = it.request()
            val newHttp = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + this.tokenManager)
                .build()
            it.proceed(newHttp)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(api_interceptor)
            .build();

        return Retrofit.Builder()
            .baseUrl(BuildConfig.TEST_BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiaryApiProvider::class.java);
    }

}
