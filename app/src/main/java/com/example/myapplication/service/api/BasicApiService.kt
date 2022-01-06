package com.example.myapplication.service.api

import com.example.myapplication.BuildConfig
import com.example.myapplication.service.api.dto.UserResponseDto
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BasicApiService {

    var provider: BasicApiProvider;

    constructor(){
        this.provider = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BasicApiProvider::class.java);
    }

    public fun login(accessToken: String, callback: Callback<UserResponseDto>){
        val body = HashMap<String, String>();
        body["accessToken"] = accessToken;
        this.provider.login(body).enqueue(callback);
    }

    fun getUser(){

    }

}