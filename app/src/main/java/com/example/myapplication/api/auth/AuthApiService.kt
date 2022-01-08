package com.example.myapplication.api.auth

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.api.auth.dto.LoginRequestDto
import com.example.myapplication.api.auth.dto.LoginResponseDto
import com.example.myapplication.api.auth.dto.RegisterRequestDto
import com.example.myapplication.api.auth.dto.RegisterResponseDto
import com.example.myapplication.util.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class AuthApiService {

    private val apiProvider: AuthApiProvider;

    constructor(tokenManager: TokenManager){
        this.apiProvider= getProvider(tokenManager);
    }

    private fun getProvider(tokenManager: TokenManager): AuthApiProvider {
        val apiInterceptor = Interceptor {
            val originalRequest = it.request()
            val newHttp = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + tokenManager.getJWT())
                .build()
            it.proceed(newHttp)
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiProvider::class.java)
    }


    fun login(
         loginRequestDto: LoginRequestDto,
         success: (LoginResponseDto?) -> Unit,
         fail: ((Throwable) -> Unit)?,
    ) : Unit {
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
    ): Unit {
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

}
