package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.service.api.BasicApiService
import com.example.myapplication.service.api.dto.UserResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var tokenManager: TokenManager? = null;
    private var apiService: BasicApiService? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tokenManager = TokenManager(applicationContext);
        apiService = BasicApiService();


        binding.diaryAddBtn.setOnClickListener {
            val intent = Intent(this, CharacterInitActivity::class.java)
            startActivity(intent)
        }

        apiService!!.login("g5Td7GQ_96p7pYaym6jkzZnmHzUqd_GH1Cj8Rwo9cpgAAAF-Lxjxhg", object: Callback<UserResponseDto>{
            override fun onResponse(
                call: Call<UserResponseDto>,
                response: Response<UserResponseDto>
            ) {
                println(response?.body());
            }

            override fun onFailure(call: Call<UserResponseDto>, t: Throwable) {
            }
        })


    }
}
