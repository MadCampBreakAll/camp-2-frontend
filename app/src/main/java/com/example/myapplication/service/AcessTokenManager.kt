package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class AcessTokenManager{

    private var sharedPreferences : SharedPreferences;

    constructor(context: Context) {
        this.sharedPreferences = context.getSharedPreferences(R.string.shared_preference_key.toString(), Context.MODE_PRIVATE);
    }

    fun getAccessToken(): String{
        return this.sharedPreferences.getString(R.string.kakao_access_token.toString(), "")!!;
    }

    fun setAccessToken(token: String) {
        val editor : Editor = sharedPreferences.edit();
        editor.putString(R.string.kakao_access_token.toString(), token);
        editor.apply();
    }

}