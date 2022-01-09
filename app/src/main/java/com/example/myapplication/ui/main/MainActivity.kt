package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.api.user.dto.GetMeResponseDto
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.UserCharacterBinding
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.DiaryDto
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var diaryCoverAdapter: DiaryCoverAdapter
    private lateinit var userApiService: UserApiService
    private lateinit var diaryApiService: DiaryApiService
    private lateinit var viewHandler: ViewHandler
    private lateinit var icon: UserCharacterBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        update()
    }

    private fun init(){
        val tokenManager = TokenManager(applicationContext)
        userApiService = UserApiService(tokenManager)
        diaryApiService = DiaryApiService(tokenManager)
        binding = ActivityMainBinding.inflate(layoutInflater)
        icon = binding.userCharacterIcon
        viewHandler = ViewHandler(this);
        diaryCoverAdapter = DiaryCoverAdapter(this)
        diaryCoverAdapter.diaryList = mutableListOf<DiaryDto>();
        binding.diaryList.adapter = diaryCoverAdapter;
        binding.diaryList.setLayoutManager(GridLayoutManager(this, 2))
    }

    private fun bind() {
        setContentView(binding.root)
        binding.diaryAddBtn.setOnClickListener {
            viewHandler.goCreateDiaryActivity();
        }
        binding.goFriendActivity.setOnClickListener {
            viewHandler.goFriendActivity();
        }
    }

    private fun update(){
        userApiService.getMe(
            success = getUserHandler,
            fail = null
        )
        diaryApiService.getDiaries(
            success = getMyDiariesHandler,
            fail = null
        )
    }

    private val getUserHandler : (GetMeResponseDto?) -> Unit = handler@{ response ->
        try {
            if(!response?.status!!){
                throw Throwable()
            }

            val (_, user) = response
            val (_, nickname, body, bodyColor, blushColor, item) = user!!
            val userCharacter = Character(body!!, bodyColor!!, blushColor!!, item!!)
            CharacterViewer(
                this,
                binding.userCharacterIcon,
                userCharacter
            ).show()
            setUserNickname(nickname = nickname ?:"unknown")
            Setting.backgroundColor = response.user!!.backgroundColor!!
            Setting.font = response.user.font!!
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

    private fun setUserNickname(nickname: String){
        this.binding.userNickname.text = nickname
    }

     @SuppressLint("NotifyDataSetChanged")
     private val getMyDiariesHandler: (GetMyDiariesResponseDto?) -> Unit = handler@{ response ->
        try {
            if(!response?.status!!){
                throw Error()
            }

            diaryCoverAdapter.run {
                clearDiary()
                addAllDiary(response.diaries!!)
                notifyDataSetChanged()
            };

        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }
}
