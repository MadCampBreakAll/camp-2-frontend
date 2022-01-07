package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.service.api.BasicApiService
import com.example.myapplication.service.api.dto.LoginRequestDto
import com.example.myapplication.service.api.dto.UserResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var tokenManager: TokenManager? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tokenManager = TokenManager(applicationContext);

        binding.diaryAddBtn.setOnClickListener {
            val intent = Intent(this, CharacterInitActivity::class.java)
            startActivity(intent)
        }

    }
}
