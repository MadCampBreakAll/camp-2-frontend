package com.example.myapplication.api.auth

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.util.TokenManager
import com.example.myapplication.api.diary.DiaryApiProvider
import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.api.diary.dto.CreateDiaryResponseDto
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiaryApiService{

    private val apiProvider: DiaryApiProvider;

    constructor(tokenManager: TokenManager){
        this.apiProvider = getProvider(tokenManager);
    }

    private fun getProvider(tokenManager: TokenManager): DiaryApiProvider{
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
            .baseUrl(BuildConfig.TEST_BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiaryApiProvider::class.java);
    }

    fun getDiaries(
        success: (GetMyDiariesResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ){
        this.apiProvider.getDiaries().enqueue(object: Callback<GetMyDiariesResponseDto>{
            override fun onResponse(
                call: Call<GetMyDiariesResponseDto>,
                response: Response<GetMyDiariesResponseDto>
            ) {
                Log.d("DEBUG", "GET DIARIES SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<GetMyDiariesResponseDto>, t: Throwable) {
                Log.d("DEBUG", "GET DIAREIS FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun createDiary(
        dto: CreateDiaryRequestDto,
        success: (CreateDiaryResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?
    ){
        this.apiProvider.createDiary(dto).enqueue(object: Callback<CreateDiaryResponseDto>{
            override fun onResponse(
                call: Call<CreateDiaryResponseDto>,
                response: Response<CreateDiaryResponseDto>
            ) {
                Log.d("DEBUG", "CREATE DIARIES SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<CreateDiaryResponseDto>, t: Throwable) {
                Log.d("DEBUG", "CREATE DIAREIS FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }


}