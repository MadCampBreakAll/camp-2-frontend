package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.example.myapplication.api.user.dto.GetMeResponseDto
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.UserCharacterBinding
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.DiaryDto
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        binding.goFriendActivity
        initMenuButton()
        initFriendButton()
        initMySettingButton()
        initIconFixingButton()
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

    fun initMenuButton() {
        binding.menu.setOnClickListener{
            when(binding.goSetting.visibility) {
                FloatingActionButton.INVISIBLE -> openDropDownMenu()
                FloatingActionButton.VISIBLE -> closeDropDownMenu()
            }
        }
    }

    fun openDropDownMenu() {
        binding.menu
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_open_with_rotate))

        binding.goFriendActivity
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.goFriendActivity.visibility = FloatingActionButton.VISIBLE;

        binding.goSetting
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.goSetting.visibility = FloatingActionButton.VISIBLE;

        binding.goIconFixing
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.goIconFixing.visibility = FloatingActionButton.VISIBLE
    }

    fun closeDropDownMenu() {
        binding.menu
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_close_with_rotate))

        binding.goFriendActivity
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.goFriendActivity.visibility = FloatingActionButton.INVISIBLE;

        binding.goSetting
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.goSetting.visibility = FloatingActionButton.INVISIBLE;

        binding.goIconFixing
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.goIconFixing.visibility = FloatingActionButton.INVISIBLE
    }

    fun initFriendButton() {
        binding.goFriendActivity.setOnClickListener {
            closeDropDownMenu()

        }
    }

    fun initMySettingButton() {
        binding.goSetting.setOnClickListener {
            closeDropDownMenu()
            //viewholder로 화면 전환
        }
    }

    fun initIconFixingButton(){
        binding.goIconFixing.setOnClickListener {
            closeDropDownMenu()
            viewHandler.goIconFixActivity()
        }
    }
}
