package com.example.myapplication.ui.diary.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import com.example.myapplication.R

class CreateDiaryAddFriendPopupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_create_diary_add_friend_popup)
    }


}