package com.example.myapplication.api.page

import com.example.myapplication.api.page.dto.CreatePageResponseDto
import com.example.myapplication.api.page.dto.GetDiaryInnerPagesResponse
import retrofit2.Call
import okhttp3.MultipartBody
import retrofit2.http.*


interface PageApiProvider {
    @GET("/pages")
    fun getDiaryInnerPages(
        @Query("diaryId") diaryId: Int,
    ): Call<GetDiaryInnerPagesResponse>

    @Multipart
    @POST("/pages")
    fun createPage(
        @Part diaryId: MultipartBody.Part,
        @Part title: MultipartBody.Part,
        @Part body: MultipartBody.Part,
        @Part color: MultipartBody.Part,
        @Part img: MultipartBody.Part?,
    ): Call<CreatePageResponseDto>
}