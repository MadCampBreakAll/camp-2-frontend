package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.databinding.ActivityLoginBinding


import com.kakao.sdk.user.UserApiClient

class LoginActivity : FragmentActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kakaoLoginButton.setOnClickListener {

            if(UserApiClient.instance.isKakaoTalkLoginAvailable(applicationContext)){
                UserApiClient.instance.loginWithKakaoTalk(applicationContext) { token, error ->
                    if (error != null) {
                        Log.e("로그인", "로그인 실패", error)
                    }
                    else if (token != null) {
                        Log.i("로그인", "로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                println("카카오톡 없음");
            }
        };

    }

}

