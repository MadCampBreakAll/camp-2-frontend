package com.example.myapplication.api.diary

import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.api.diary.dto.CreateDiaryResponseDto
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DiaryApiProvider{
    @GET("/diaries/me")
    fun getDiaries() : Call<GetMyDiariesResponseDto>;

    @POST("/diaries")
    fun createDiary(
        @Body dto: CreateDiaryRequestDto,
    ) : Call<CreateDiaryResponseDto>;
}