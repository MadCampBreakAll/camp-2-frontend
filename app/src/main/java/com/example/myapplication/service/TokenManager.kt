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
        return getSharedPreferences(this.context).getString("test", "empty!!")!!;
    }

    fun setAccessToken(token: String) {
        val editor : Editor = getSharedPreferences(this.context).edit();
        editor.putString("test", token);
        editor.apply();
        editor.commit();
    }

}

