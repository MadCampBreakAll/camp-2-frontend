package com.example.myapplication.api.page

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.api.BasicApiService
import com.example.myapplication.api.page.dto.CreatePageRequestDto
import com.example.myapplication.api.page.dto.CreatePageResponseDto
import com.example.myapplication.api.page.dto.GetDiaryInnerPagesResponse
import com.example.myapplication.util.TokenManager
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PageApiService : BasicApiService{

    private val apiProvider: PageApiProvider;

    constructor(tokenManager: TokenManager){
        this.apiProvider= getProvider(tokenManager);
    }

    private fun getProvider(tokenManager: TokenManager): PageApiProvider {

        val httpClient = getBasicHttpClientBuilder().addInterceptor(
            getApiInterceptorWithJWT(tokenManager.getJWT())
        ).build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URI)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(
                getBasicGson()
            ))
            .build()
            .create(PageApiProvider::class.java)
    }


    fun createPage(
        createPageRequestDto: CreatePageRequestDto,
        success: (CreatePageResponseDto?) -> Unit,
        fail: ((Throwable) -> Unit)?,
    ) : Unit {
        this.apiProvider.createPage(createPageRequestDto).enqueue(object : Callback<CreatePageResponseDto> {
            override fun onResponse(
                call: Call<CreatePageResponseDto>,
                response: Response<CreatePageResponseDto>
            ) {
                Log.d("DEBUG", "LOGIN SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<CreatePageResponseDto>, t: Throwable) {
                Log.d("DEBUG", "LOGIN FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

    fun getDiaryInnerPages(
        diaryId: Int,
        success: (GetDiaryInnerPagesResponse?) -> Unit,
        fail: ((Throwable) -> Unit)?,
    ): Unit {
        this.apiProvider.getDiaryInnerPages(diaryId).enqueue(object: Callback<GetDiaryInnerPagesResponse> {
            override fun onResponse(
                call: Call<GetDiaryInnerPagesResponse>,
                response: Response<GetDiaryInnerPagesResponse>
            ) {
                Log.d("DEBUG", "GET DIARY INNER PAGES SUCCESS")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                success(response.body());
            }

            override fun onFailure(call: Call<GetDiaryInnerPagesResponse>, t: Throwable) {
                Log.d("DEBUG", "GET DIARY INNER PAGES FAIL")
                Log.d("DEBUG", t.toString())

                fail?.invoke(t);
            }
        })
    }

}
