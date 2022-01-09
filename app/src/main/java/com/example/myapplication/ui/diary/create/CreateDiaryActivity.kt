package com.example.myapplication.ui.diary.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.api.diary.dto.CreateDiaryResponseDto
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.databinding.ActivityCreateDiaryBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class CreateDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateDiaryBinding
    private lateinit var diaryApiService: DiaryApiService
    private lateinit var friendApiService: FriendApiService
    private lateinit var tokenManager: TokenManager
    private lateinit var viewHandler: ViewHandler
    private lateinit var createDiary: CreateDiary

    private var secondFriendId = null
    private var thirdFriendId = null
    private var FourthFriendId = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        bind()
    }

    private fun init(){
        binding = ActivityCreateDiaryBinding.inflate(layoutInflater)
        tokenManager = TokenManager(this)
        viewHandler = ViewHandler(this)
        diaryApiService = DiaryApiService(tokenManager)
        friendApiService = FriendApiService(tokenManager)
    }

    private fun bind(){
        setContentView(binding.root);
        binding.createDiaryButton.setOnClickListener {
//            val dto = CreateDiaryRequestDto(
//                title = binding.title.toString(),
//                userIds = arrayListOf(
//                    binding.friendId1.text.toString(),
//                    binding.friendId1.text.toString(),
//                ),
//            )
            val dto = CreateDiaryRequestDto(
                title = binding.title.toString(),
                userIds = arrayListOf(
                    "12345",
                    "11111",
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