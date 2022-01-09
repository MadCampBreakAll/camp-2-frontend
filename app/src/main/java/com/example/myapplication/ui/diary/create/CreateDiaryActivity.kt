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
    private lateinit var viewHandler: ViewHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
    }

    private fun init(){
        binding = ActivityCreateDiaryBinding.inflate(layoutInflater);
        viewHandler = ViewHandler(this)
        diaryApiService = DiaryApiService(
            TokenManager(this)
        )
    }

    private fun bind(){
        setContentView(binding.root);
        binding.createDiaryButton.setOnClickListener {
            val dto = CreateDiaryRequestDto(
                title = binding.title.toString(),
                userIds = arrayListOf(
                    binding.friendId1.text.toString(),
                    binding.friendId1.text.toString(),
                ),
            )
            diaryApiService.createDiary(dto, success = createDiaryHandler, fail = null);
        }
    }

    private val createDiaryHandler : (CreateDiaryResponseDto?) -> Unit = createDiaryHandler@{ response ->
        try {
            if(!response!!.status!!){
                throw Error()
            }

            viewHandler.goMainActivity()
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }
}