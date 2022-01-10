package com.example.myapplication.ui.page.create

import android.graphics.Color
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreatePageBinding
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog
import android.widget.EditText
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.graphics.convertTo
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.CreateDiaryRequestDto
import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.api.page.dto.CreatePageRequestDto
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import androidx.lifecycle.Observer
import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.api.page.dto.CreatePageRequestDto
import com.example.myapplication.ui.main.DiaryCoverAdapter
import com.example.myapplication.ui.main.Setting
import com.example.myapplication.util.TokenManager

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
        binding.innerPageWrittenDate.text = createDate()
        binding.innerPageDailyColor.setColorFilter(Color.parseColor(dailyColor))
        setContentView(binding.root)
    }

    fun createDate(): String{
        val sdf = SimpleDateFormat("yyyy/M/dd hh:mm")
        val currentDate = sdf.format(Date())
        return currentDate;
    }

    @RequiresApi(Build.VERSION_CODES.O)

    }

    fun init(){
        binding = ActivityCreatePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tokenManager = TokenManager(this)
        pageApiService = PageApiService(tokenManager)

        Setting.setting.observe(this, Observer {
            updateBackground()
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
            pageApiService.createPage(
                CreatePageRequestDto(
                    diaryId!!,
                    pageTitle.toString(),
                    body.toString(),
                    color
                ),
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