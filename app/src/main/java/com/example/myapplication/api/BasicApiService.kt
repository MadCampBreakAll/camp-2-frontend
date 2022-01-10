package com.example.myapplication.api

import com.example.myapplication.util.TokenManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient

open class BasicApiService {

    protected fun getBasicGson() : Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    }

    protected fun getApiInterceptorWithJWT(token: String): Interceptor{
        return Interceptor {
            println("${token} in interceptor!!!")
            val originalRequest = it.request()
            val newHttp = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build()
            it.proceed(newHttp)
        }
    }

    protected fun getBasicHttpClientBuilder() : OkHttpClient.Builder{
        return OkHttpClient.Builder()
    }
}