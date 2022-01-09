package com.example.myapplication.ui.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.myapplication.R
import com.example.myapplication.api.auth.AuthApiService
import com.example.myapplication.api.auth.dto.RegisterRequestDto
import com.example.myapplication.api.auth.dto.RegisterResponseDto
import com.example.myapplication.databinding.ActivityNicknameSettingBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class NicknameSettingActivity : AppCompatActivity() {
    private lateinit var authApiService: AuthApiService
    private lateinit var tokenManager: TokenManager
    private lateinit var viewHandler: ViewHandler
    private lateinit var binding: ActivityNicknameSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname_setting)
        init()
        bind()
    }

    fun init() {
        binding = ActivityNicknameSettingBinding.inflate(layoutInflater)
        this.tokenManager = TokenManager(this);
        this.authApiService = AuthApiService(tokenManager)
        this.viewHandler = ViewHandler(this)
    }

    fun bind() {
        var nickname = binding.editTextNickname.text.toString()

        binding.createNicknameBtn.setOnClickListener {
            val registerRequestDto = RegisterRequestDto(
                tokenManager.getAccessToken(),
                nickname = "TEST",
                body = CharacterInitActivity.character_init_body_shape,
                bodyColor = CharacterInitActivity.character_init_body_color,
                font = 0,
                item = CharacterInitActivity.character_init_item,
                blushColor = CharacterInitActivity.character_init_blush,
            );
            authApiService.register(
                registerRequestDto,
                success = registerHandler,
                fail = null
            );
        }
    }

    var registerHandler : (RegisterResponseDto? ) -> Unit = handler@{ response ->
        try {
            if(!response!!.status!!){
                throw Error()
            }else {
                this.tokenManager.setJWT(response!!.token!!);
                viewHandler.goMainActivity();
            }
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }
}