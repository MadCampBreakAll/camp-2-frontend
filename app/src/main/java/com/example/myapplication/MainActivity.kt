package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.api.user.UserApiProvider
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.dto.GetMeResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var tokenManager: TokenManager? = null;
    private var userApiProvider: UserApiProvider? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(applicationContext);
        userApiProvider = UserApiService(tokenManager!!).getProvider();
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userApiProvider!!.getMe().enqueue(object : Callback<GetMeResponseDto> {
            override fun onResponse(
                call: Call<GetMeResponseDto>,
                response: Response<GetMeResponseDto>
            ) {
                println(response.body());
            }

            override fun onFailure(call: Call<GetMeResponseDto>, t: Throwable) {
                println(t);
            }
        })

        bindLayouts();
    }

    fun bindLayouts(){
        binding.diaryAddBtn.setOnClickListener {
            val intent = Intent(this, CharacterInitActivity::class.java)
            startActivity(intent)
        }
    }
}
