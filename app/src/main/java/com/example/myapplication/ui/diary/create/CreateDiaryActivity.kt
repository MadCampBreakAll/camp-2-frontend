package com.example.myapplication.ui.diary.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.api.diary.dto.CreateDiaryResponseDto
import com.example.myapplication.databinding.ActivityCreateDiaryBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class CreateDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateDiaryBinding
    private lateinit var diaryApiService: DiaryApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind()

        diaryApiService = DiaryApiService(
            TokenManager(this)
        );
    }

    private fun bind(){
        binding = ActivityCreateDiaryBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.createDiaryButton.setOnClickListener {
            val dto = CreateDiaryRequestDto(
                title = binding.title.toString(),
                userIds = arrayListOf(
                    binding.friendId1.text.toString(),
                    binding.friendId1.text.toString(),
                ),
            );

            diaryApiService.createDiary(dto, success = createDiaryHandler, fail = null);
        }
    }

    private val createDiaryHandler : (CreateDiaryResponseDto?) -> Unit = createDiaryHandler@{ response ->
        val handler = ViewHandler(this);

        if(
            handler.goLoginActivityIfNull(response) ||
            handler.goLoginActivityIfNull(response?.diary) ||
            handler.goLoginActivityIfNull(response?.status)
                ) {
                    return@createDiaryHandler
        }

        val dto = response!!;

        val status = dto.status!!
        val diary = dto.diary!!

        if(status){
            handler.goMainActivity();
            return@createDiaryHandler
        }

        Log.d("DEBUG", "DIARY 생성에 실패했습니다.");
    }
}