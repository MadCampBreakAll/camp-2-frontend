package com.example.myapplication.ui.setting.backfont

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.user.dto.UpdateUIRequestDto
import com.example.myapplication.databinding.ActivitySettingBackgroundBinding
import com.example.myapplication.ui.main.Setting
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.TokenManager
import com.google.gson.annotations.SerializedName

class SettingBackgroundActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBackgroundBinding
    private lateinit var backgroundColorAdapter: BackgroundColorAdapter
    private lateinit var userApiService: UserApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBackgroundBinding.inflate(layoutInflater)
        backgroundColorAdapter = BackgroundColorAdapter(this, setColor)

        binding.colorGrid.layoutManager = GridLayoutManager(this, 3)
        binding.colorGrid.adapter = backgroundColorAdapter
        userApiService = UserApiService(
            TokenManager(this)
        )
        setContentView(binding.root)
        init()
        bind()
    }

    private fun bind(){
        binding.monoonInnerPage.setOnClickListener{
            Setting.page = 1
            updateSettings(Setting)
            updateView(Setting)
        }

        binding.normalInnerPage.setOnClickListener{
            Setting.page = 0
            updateSettings(Setting)
            updateView(Setting)
        }
    }

    private fun init(){
        userApiService.getMe(
            success = {dto ->
                try {
                    val (status, user) = dto!!
                    if(!status!!){
                        throw Throwable()
                    }
                    val (_, _, body, bodyColor, blushColor, item, _font, _backgroundColor, backgroundPaper) = user!!
                    Setting.page = backgroundPaper!!
                    Setting.backgroundColor = _backgroundColor!!
                    Setting.font = _font!!
                    updateView(Setting)

                    CharacterViewer(
                        this,
                        binding.userCharacter,
                        Character(
                            body!!,
                            bodyColor!!,
                            blushColor!!,
                            item!!
                        )
                    ).show()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            },
            fail = null
        )
    }

    private fun updateView(setting: Setting){
        binding.monoonBackground.visibility = View.INVISIBLE
        if(setting.page == 1) {
            binding.monoonBackground.visibility = View.VISIBLE
        }
        if(setting.page == 0) {
            binding.monoonBackground.visibility = View.INVISIBLE
        }
        binding.testingViewColor.setBackgroundColor(Color.parseColor(setting.backgroundColor))
    }

    private fun updateSettings(setting: Setting){
        val dto = UpdateUIRequestDto(
            setting.font,
            setting.backgroundColor,
            setting.page
        )
        println(dto)
        userApiService.updateUI(dto,
            success = { dto ->
                try {
                    if(!dto!!.status!!){
                        throw Throwable()
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            },
            fail = null
        )
    }

    private val setColor : (String) -> Unit = { color ->
        Setting.backgroundColor = color
        updateSettings(Setting)
        updateView(Setting)
    }
}