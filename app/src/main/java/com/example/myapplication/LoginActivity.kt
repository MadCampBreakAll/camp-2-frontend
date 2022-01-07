package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.api.auth.AuthApiProvider
import com.example.myapplication.api.auth.AuthApiService
import com.example.myapplication.api.dto.LoginRequestDto
import com.example.myapplication.api.dto.LoginResponseDto
import com.kakao.sdk.auth.model.OAuthToken


import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var tokenManager: TokenManager;
    private lateinit var authApiProvider: AuthApiProvider;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        tokenManager = TokenManager(this);
        authApiProvider = AuthApiService(tokenManager).getProvider();
        setContentView(binding.root)

        if(tokenManager.getJWT().equals(TokenManager.STATUS.EMPTY_JWT)){
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
            Log.d("KAKAO", "로그인 성공 ${token.accessToken}")


            tokenManager.setAccessToken(token.accessToken);

            val loginRequestDto = LoginRequestDto(token.accessToken);
            authApiProvider.login(loginRequestDto).enqueue(object :
                retrofit2.Callback<LoginResponseDto> {
                override fun onResponse(
                    call: Call<LoginResponseDto>,
                    response: Response<LoginResponseDto>
                ) {
                    successLoginCallback(response);
                }
                override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                    Log.d("DEBUG", "login 실패")
                }
            })

        }
    }

    fun successLoginCallback(responseDto: Response<LoginResponseDto>){
        if(responseDto.body()!!.status){
            this.tokenManager.setJWT(responseDto.body()!!.token);
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
            finish();
        } else {
            Log.d("DEBUG", responseDto.toString());
        }
    }

}


