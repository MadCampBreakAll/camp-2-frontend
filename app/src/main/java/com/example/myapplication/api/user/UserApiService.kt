package com.example.myapplication.api.user

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.TokenManager
import com.example.myapplication.api.dto.GetMeResponseDto
import okhttp3.Interceptor
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
            .baseUrl(BuildConfig.BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiProvider::class.java);
    }

}