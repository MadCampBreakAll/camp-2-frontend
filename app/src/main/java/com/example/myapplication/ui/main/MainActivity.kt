package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.example.myapplication.api.user.dto.GetMeResponseDto
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.UserCharacterBinding
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto
import com.example.myapplication.ui.main.Setting.backgroundColor
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
        val tokenManager = TokenManager(this)
        userApiService = UserApiService(tokenManager)
        diaryApiService = DiaryApiService(tokenManager)
        binding = ActivityMainBinding.inflate(layoutInflater)
        icon = binding.userCharacterIcon
        viewHandler = ViewHandler(this);
        diaryCoverAdapter = DiaryCoverAdapter(this)
        binding.diaryList.adapter = diaryCoverAdapter
        binding.diaryList.setLayoutManager(GridLayoutManager(this, 2))
        Setting.setting.observe(this, Observer {  setting ->
            updateBackground()
            updateFont()
        })
    }

    private fun bind() {
        setContentView(binding.root)
        binding.diaryAddBtn.setOnClickListener {
            viewHandler.goCreateDiaryActivity();
        }
        binding.root.setOnRefreshListener {
            update()
            binding.root.isRefreshing = false
        }
        initMenuButton()
        initFriendButton()
        initMySettingButton()
        initIconFixingButton()
    }

    override fun onResume() {
        super.onResume()
        update()
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

    private fun updateBackground(){
        binding.backgroundColor.setBackgroundColor(
            Color.parseColor(Setting.backgroundColor)
        )
        if(Setting.page == 0){
            binding.monoonBackground.visibility = View.INVISIBLE
        }
        else {
            binding.monoonBackground.visibility = View.VISIBLE
        }

    }

    private fun updateFont(){

    }

    private val getUserHandler : (GetMeResponseDto?) -> Unit = handler@{ response ->
        try {
            if(!response?.status!!){
                throw Throwable()
            }

            val (_, user, _) = response
            val (id, nickname, body, bodyColor, blushColor, item) = user!!
            val userCharacter = Character(body!!, bodyColor!!, blushColor!!, item!!)

            CharacterViewer(
                this,
                binding.userCharacterIcon,
                userCharacter
            ).show()

            this.diaryCoverAdapter.setMyId(id!!)
            diaryCoverAdapter.notifyDataSetChanged()
            setUserNickname(nickname = nickname ?:"unknown")
            Setting.backgroundColor = response.user!!.backgroundColor!!
            Setting.font = response.user.font!!
            Setting.page = response.user.backgroundPaper!!
            updateBackground()
            updateFont()

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
            }
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

    private fun closeDropDownMenu() {
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

    private fun initFriendButton() {
        binding.goFriendActivity.setOnClickListener {
            closeDropDownMenu()
            viewHandler.goFriendActivity()
        }
    }

    private fun initMySettingButton() {
        binding.goSetting.setOnClickListener {
            closeDropDownMenu()
            viewHandler.goBackgroundSetting()
        }
    }

    private fun initIconFixingButton(){
        binding.goIconFixing.setOnClickListener {
            closeDropDownMenu()
            viewHandler.goIconFixActivity()
        }
    }

}
