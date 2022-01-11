package com.example.myapplication.ui.page.create

import android.graphics.Color
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreatePageBinding
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog
import android.widget.EditText
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.convertTo
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.util.ViewHandler
import java.util.*
import androidx.lifecycle.Observer
import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.api.page.dto.CreatePageRequestDto
import com.example.myapplication.ui.main.Setting
import com.example.myapplication.ui.singleton.DiaryResponseSingleton
import com.example.myapplication.ui.singleton.UserResponseSingleton
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.SimpleDate
import com.example.myapplication.util.TokenManager
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

class CreatePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePageBinding
    private lateinit var pageApiService: PageApiService
    private lateinit var viewHandler: ViewHandler
    private var dailyColor = "#fff1e6"
    private var diaryId: Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        bind()

        try {
            diaryId = intent.getIntExtra(
                "diaryId",
                -1
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

    fun init(){
        viewHandler = ViewHandler(this)
        pageApiService = PageApiService(
            TokenManager(this)
        )
        binding = ActivityCreatePageBinding.inflate(layoutInflater)
        binding.innerPageWrittenDate.text = SimpleDate.of(Date())
        binding.innerPageDailyColor.setColorFilter(Color.parseColor(dailyColor))
        setContentView(binding.root)
        Setting.setting.observe(this, Observer {
            updateBackground()
        })

        DiaryResponseSingleton.getMyDiariesResponseDto.observe(this, Observer { dto ->
            try {
                val diary = dto!!.diaries?.findLast { it.id == diaryId }!!
                val chameyeos = diary.chamyeoUsers
                val nextUser = diary.nextUser
                var nextNextUserIndex = -1
                for(chameyoIndex: Int in 0 until chameyeos.size) {
                    if(chameyeos.get(chameyoIndex).id!! == nextUser.id!!) {
                        nextNextUserIndex = (chameyoIndex + chameyeos.size + 1)%chameyeos.size
                    }
                }
                val nextNextUser = chameyeos[nextNextUserIndex]
                val (_, nickname, body, bodyColor, blushColor, item) = nextNextUser
                binding.nextUserNickname.text = nickname
                val nextNextUserCharacter = binding.innerPageNextUserCharacter
                CharacterViewer(
                    this,
                    nextNextUserCharacter,
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
        })

        UserResponseSingleton.getMeResponseDto.observe(this, Observer { dto ->
            try {
                val (_, _, body, bodyColor, blushColor, item) = dto!!.user!!
                binding.writerNickname.text = dto.user!!.nickname
                val nextUser = binding.innerPageWriteUserCharacter
                CharacterViewer(
                    this,
                    nextUser,
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
        })
    }

    fun updateBackground() {
        binding.pageBackgroundColor.setBackgroundColor(Color.parseColor(Setting.backgroundColor))

        if(Setting.page == 0) {
            binding.monoonBackground.visibility = View.INVISIBLE
        }
        else {
            binding.monoonBackground.visibility = View.VISIBLE
        }
    }

    fun bind(){
        binding.innerPageDailyColor.setOnClickListener{
            val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
                .setInitialColor(Color.parseColor(dailyColor))
                .setColorModel(ColorModel.RGB)
                .setColorModelSwitchEnabled(true)
                .setButtonOkText(android.R.string.ok)
                .setButtonCancelText(android.R.string.cancel)
                .onColorSelected{ color: Int ->
                    dailyColor = "#${"%06X".format(color + 16777216)}"
                    binding.innerPageDailyColor.setColorFilter(color)
                }
                .create()

            colorPicker.show(supportFragmentManager, "color_picker")
        }


        binding.innerPageCompleteBtn.setOnClickListener {

            val pageTitle = binding.pageTitle.text
            val body = binding.innerPageText.text
            val color = dailyColor

            if(pageTitle.isBlank()){
                Toast.makeText(this, "오늘 하루의 제목을 정해주세요!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            pageApiService.createPage(
                MultipartBody.Part.createFormData("diaryId", diaryId!!.toString()),
                MultipartBody.Part.createFormData("title", pageTitle.toString()),
                MultipartBody.Part.createFormData("body", body.toString()),
                MultipartBody.Part.createFormData("color", color),
                // TODO img 형태로 전송
                MultipartBody.Part.createFormData("img", color),
                success = {dto ->
                    try {
                        if(dto!!.login != null && dto.login === false) {
                            throw Throwable()
                        }
                        finish()
                    } catch (e : Throwable) {
                        e.printStackTrace()
                    }
                },
                fail = null
            )
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view: View? = currentFocus
        if (view != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && (view is EditText)) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x: Float = ev.rawX + view.getLeft() - scrcoords[0]
            val y: Float = ev.rawY + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager).hideSoftInputFromWindow(
                this.window.decorView.applicationWindowToken, 0
            )
        }
        return super.dispatchTouchEvent(ev)
    }
}