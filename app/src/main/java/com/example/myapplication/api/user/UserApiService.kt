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
            .baseUrl(BuildConfig.BASE_URI)
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




}