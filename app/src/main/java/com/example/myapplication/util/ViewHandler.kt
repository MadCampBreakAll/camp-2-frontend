package com.example.myapplication.util

import android.app.Activity
import android.content.Intent
import com.example.myapplication.ui.diary.create.CreateDiaryActivity
import com.example.myapplication.ui.diary.create.CreateDiaryAddFriendPopupActivity
import com.example.myapplication.ui.friend.FriendActivity
import com.example.myapplication.ui.friend.SearchUserDialog
import com.example.myapplication.ui.join.CharacterInitActivity
import com.example.myapplication.ui.join.NicknameSettingActivity
import com.example.myapplication.ui.login.LoginActivity
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.page.DiaryInnerActivity
import com.example.myapplication.ui.page.create.CreateImagePageActivity
import com.example.myapplication.ui.page.create.CreatePageActivity
import com.example.myapplication.ui.setting.backfont.SettingBackgroundActivity
import com.example.myapplication.ui.setting.icon.CharacterFixActivity

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
    
        return true
    }

    fun goMainActivityAndFinish(): Boolean {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        return true
    }

    fun goCharacterInitActivity() : Boolean {
        val intent = Intent(activity, CharacterInitActivity::class.java)
        activity.startActivity(intent)
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
        return true;
    }

    fun goFriendActivity(): Boolean {
        val intent = Intent(activity, FriendActivity::class.java)
        activity.startActivity(intent)
        return true
    }

    fun goPendingFriendActivity(): Boolean{
        val intent = Intent(activity, SearchUserDialog::class.java)
        activity.startActivity(intent)
        return true;
    }

    fun goDiaryInnerActivityWithDiaryId(diaryId: Int) {
        val intent = Intent(activity, DiaryInnerActivity::class.java)
        intent.putExtra("diaryId", diaryId)
        activity.startActivity(intent)
    }

    fun goAddFriendPopupActivity(friendSeq: Int){
        val intent = Intent(activity, CreateDiaryAddFriendPopupActivity::class.java)
        intent.putExtra("friendSeq", friendSeq)
        activity.startActivity(intent)
    }

    fun goNicknameActivity(){
        val intent = Intent(activity, NicknameSettingActivity::class.java)
        activity.startActivity(intent)
    }

    fun goIconFixActivity(){
        val intent = Intent(activity, CharacterFixActivity::class.java)
        activity.startActivity(intent)
    }

    fun goBackgroundSetting(){
        val intent = Intent(activity, SettingBackgroundActivity::class.java)
        activity.startActivity(intent)
    }

    fun goCreatePageAcitivty(diaryId: Int) {
        val intent = Intent(activity, CreatePageActivity::class.java)
        intent.putExtra("diaryId", diaryId)
        activity.startActivity(intent)
    }

    fun goCreateImagePageActivity(diaryId: Int) {
        val intent = Intent(activity, CreateImagePageActivity::class.java)
        intent.putExtra("diaryId", diaryId)
        activity.startActivity(intent)
    }
}