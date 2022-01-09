package com.example.myapplication.api.page

import com.example.myapplication.api.page.dto.CreatePageRequestDto
import com.example.myapplication.api.page.dto.CreatePageResponseDto
import com.example.myapplication.api.page.dto.GetDiaryInnerPagesResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PageApiProvider {
    @POST("/pages")
    fun createPage(
        @Body Create: CreatePageRequestDto,
    ) : Call<CreatePageResponseDto>

    @GET("/pages")
    fun getDiaryInnerPages(
        @Query("diaryId") diaryId: Int,
    ) : Call<GetDiaryInnerPagesResponse>
}