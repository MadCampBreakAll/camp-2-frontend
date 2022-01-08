package com.example.myapplication.ui.login

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.api.auth.AuthApiService
import com.example.myapplication.api.auth.dto.LoginRequestDto
import com.example.myapplication.api.auth.dto.LoginResponseDto
import com.example.myapplication.api.user.UserApiProvider
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler
import com.kakao.sdk.auth.model.OAuthToken


import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authApiService: AuthApiService
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
    }

    private fun init(){
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tokenManager = TokenManager(this)
        authApiService = AuthApiService(tokenManager)
    }

    private fun bind(){
        binding.kakaoLoginButton.setOnClickListener {
            if(!this.authApiService.loginWithKaKao(
                    this,
                    callback = loginWithKaKaoHandler
            )) {
                val viewHandler = ViewHandler(this);
                viewHandler.goMainActivity();
            }
        }
    }

    private val loginWithKaKaoHandler : (OAuthToken?, Throwable?) -> Unit = handler@{ token, error ->

        if(token == null){
            return@handler
        }

        val accessToken = token.accessToken

        tokenManager.setAccessToken(accessToken)
        val loginRequestDto = LoginRequestDto(accessToken)
        authApiService.login(
            loginRequestDto,
            success = loginWithServerHandler,
            fail = null,
        )

    }

    private val loginWithServerHandler : (LoginResponseDto?) -> Unit = handler@{ response ->
        val viewHandler = ViewHandler(this);

        try {
            if(response?.register != null && response.register == false){
                viewHandler.goCharacterInitActivity()
                return@handler
            }

            if(response!!.status!! == false){
                viewHandler.goLoginActivityAndRemoveTokens()
                return@handler
            }

            if(response!!.status!! == true){
                this.tokenManager.setJWT(response.token!!);
                viewHandler.goMainActivity();
                return@handler
            }
            throw Throwable()
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
            return@handler
        }

    }

}


