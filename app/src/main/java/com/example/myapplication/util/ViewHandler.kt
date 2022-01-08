package com.example.myapplication.util

import android.app.Activity
import android.content.Intent
import com.example.myapplication.ui.join.CharacterInitActivity
import com.example.myapplication.ui.login.LoginActivity
import com.example.myapplication.ui.main.MainActivity

class ViewHandler {
    var activity: Activity;
    constructor(activity: Activity){
        this.activity = activity;
    }

    fun goLoginActivityAndRemoveTokens(): Boolean {
        val tokenManager = TokenManager(this.activity) ;
        tokenManager.removeJWT();
        tokenManager.removeAccessToken()

        val intent = Intent(activity, LoginActivity::class.java);
        activity.startActivity(intent);
        activity.finish()
        return true;
    }

    fun goLoginActivityIfNull(test: Any?): Boolean{
        if(test == null){
            val intent = Intent(activity, LoginActivity::class.java);
            activity.startActivity(intent);
            activity.finish()
            return true;
        }

        return false;
    }

    fun goMainActivity() : Boolean{
        val intent = Intent(activity, MainActivity::class.java);
        activity.startActivity(intent);
        activity.finish()
        return true;
    }

    fun goCharacterInitActivity() : Boolean {
        val intent = Intent(activity, CharacterInitActivity::class.java);
        activity.startActivity(intent);
        activity.finish()
        return true;
    }
}