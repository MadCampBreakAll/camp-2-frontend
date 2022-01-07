package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken


import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var tokenManager: TokenManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        tokenManager = TokenManager(this);
        setContentView(binding.root)

        if(tokenManager.getAccessToken().equals(TokenManager.STATUS.EMPTY_ACCESS_TOKEN)){
            binding.kakaoLoginButton.setOnClickListener {
                if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                    UserApiClient.instance.loginWithKakaoTalk(this, callback = callback);
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback);
                }
            };
        } else {
            var intent = Intent(this, MainActivity::class.java);
            finish();
            startActivity(intent);
        }

    }

    // 로그인 공통 callback 구성
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("KAKAO", "로그인 실패", error)
        }
        else if (token != null) {
            Log.i("KAKAO", "로그인 성공 ${token.accessToken}")
            tokenManager.setAccessToken(token.accessToken);
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
            finish();
        }
    }

}


