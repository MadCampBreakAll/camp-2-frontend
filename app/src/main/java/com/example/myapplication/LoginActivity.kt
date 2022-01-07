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
import java.lang.Error

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

        binding.kakaoLoginButton.setOnClickListener {
            loginWithKaKao();
        };
    }

    private fun loginWithKaKao(){
        if(tokenManager.getJWT() == TokenManager.STATUS.EMPTY_JWT){
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback);
                return;
            }

            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback);
            return;
        }

        var intent = Intent(this, MainActivity::class.java);
        startActivity(intent);
        finish();
    }

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->

        if (error != null) {
            Log.e("DEBUG", "로그인 실패", error);
        }

        if (token != null) {
            Log.d("DEBUG", "로그인 성공 ${token.accessToken}")

            loginWithServer(token.accessToken);
        }
    }

    private fun loginWithServer(accessToken: String){

        tokenManager.setAccessToken(accessToken);
        val loginRequestDto = LoginRequestDto(accessToken);

        authApiProvider.login(loginRequestDto).enqueue(object :
            retrofit2.Callback<LoginResponseDto> {

            override fun onResponse(
                call: Call<LoginResponseDto>,
                response: Response<LoginResponseDto>
            ) {
                Log.d("DEBUG", "SERVER LOGIN 성공")
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.body().toString())

                loginWithServerHandler(response.body()!!);
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                Log.d("DEBUG", "login 실패")
            }
        })

    }

    private fun loginWithServerHandler(responseDto: LoginResponseDto){
        if(responseDto == null){
            throw Error("login body가 null입니다.");
        }

        if(!responseDto.status) {
            val intent = Intent(this, CharacterInitActivity::class.java);
            startActivity(intent);
            finish();
            return;
        }

        this.tokenManager.setJWT(responseDto.token);
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent);
        finish();
    }

}


