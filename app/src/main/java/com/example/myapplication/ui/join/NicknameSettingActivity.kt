package com.example.myapplication.ui.join

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        binding = ActivityNicknameSettingBinding.inflate(layoutInflater)

        setContentView(binding.root)
        init()
        bind()
    }

    fun init() {
        this.tokenManager = TokenManager(this);
        this.authApiService = AuthApiService(tokenManager)
        this.viewHandler = ViewHandler(this)
    }

    fun bind() {
        binding.createNicknameBtn.setOnClickListener handler@{
            var nickname = binding.editTextNickname.text.toString()
            if (nickname.length <= 2) {
                binding.checkNicknameText.text = "닉네임은 세 글자 이상으로 설정해 주십시오."
                binding.checkNicknameText.visibility= View.VISIBLE
                return@handler
            }

            val registerRequestDto = RegisterRequestDto(
                tokenManager.getAccessToken(),
                nickname = nickname,
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