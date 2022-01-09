package com.example.myapplication.util

import android.app.Activity
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.diary.create.CreateDiaryActivity
import com.example.myapplication.ui.friend.FriendActivity
import com.example.myapplication.ui.friend.PendingFriendActivity
import com.example.myapplication.ui.join.CharacterInitActivity
import com.example.myapplication.ui.login.LoginActivity
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.page.DiaryInnerActivity

class ViewHandler {
    private var activity: Activity
    private var tokenManager: TokenManager

    constructor(activity: Activity){
        this.activity = activity;
        this.tokenManager = TokenManager(this.activity)
    }

    fun goLoginActivityAndRemoveTokens(): Boolean {
        tokenManager.removeJWT();
        tokenManager.removeAccessToken()

        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
        return true
    }

    fun goLoginActivityIfNull(test: Any?): Boolean{
        if(test == null){
            tokenManager.removeJWT();
            tokenManager.removeAccessToken()

            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()

            return true
        }

        return false
    }

    fun goMainActivity() : Boolean{
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
        return true
    }

    fun goCharacterInitActivity() : Boolean {
        val intent = Intent(activity, CharacterInitActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
        return true
    }

    fun goCreateDiaryActivity() : Boolean {
        val intent = Intent(activity, CreateDiaryActivity::class.java)
        activity.startActivity(intent)
        return true;
    }

    fun goCreateDiaryAndFinish() : Boolean {
        val intent = Intent(activity, CreateDiaryActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
        return true;
    }

    fun goFriendActivity(): Boolean {
        val intent = Intent(activity, FriendActivity::class.java)
        activity.startActivity(intent)
        return true
    }

    fun goPendingFriendActivity(): Boolean{
        val intent = Intent(activity, PendingFriendActivity::class.java)
        activity.startActivity(intent)
        return true;
    }

    fun goDiaryInnerActivityWithDiaryId(diaryId: Int) {
        val intent = Intent(activity, DiaryInnerActivity::class.java)
        intent.putExtra("diaryId", diaryId)
        activity.startActivity(intent)
    }
}