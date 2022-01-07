package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class TokenManager{

    private var context: Context;

    constructor(context: Context) {
        this.context = context;
    }

    private fun getString(sourceId: Int): String {
        return context.getString(sourceId);
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
    }

    fun getAccessToken(): String{
        return getSharedPreferences(this.context).getString(getString(R.string.kakao_access_token), TokenManager.STATUS.EMPTY_ACCESS_TOKEN)!!;
    }

    fun setAccessToken(token: String) {
        val editor : Editor = getSharedPreferences(this.context).edit();
        editor.putString(getString(R.string.kakao_access_token), token);
        editor.apply();
    }

    fun getJWT(): String {
        return getSharedPreferences(this.context).getString(getString(R.string.json_web_token), TokenManager.STATUS.EMPTY_JWT)!!;
    }

    fun setJWT(token: String) {
        val editor: Editor = getSharedPreferences(this.context).edit();
        editor.putString(getString(R.string.json_web_token), token);
        editor.apply();
    }

    object STATUS {
        var EMPTY_JWT = "EMPTY_JWT";
        var EMPTY_ACCESS_TOKEN = "EMPTY_ACCESS_TOKEN";
    }

}

