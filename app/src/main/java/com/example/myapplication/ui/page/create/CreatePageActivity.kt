package com.example.myapplication.ui.page.create

import android.graphics.Color
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.myapplication.databinding.ActivityCreatePageBinding
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog
import android.widget.EditText
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import com.example.myapplication.util.ViewHandler
import java.util.*
import androidx.lifecycle.Observer
import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.ui.main.Setting
import com.example.myapplication.ui.singleton.DiaryResponseSingleton
import com.example.myapplication.ui.singleton.UserResponseSingleton
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.SimpleDate
import com.example.myapplication.util.TokenManager
import gun0912.tedbottompicker.TedBottomPicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class CreatePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePageBinding
    private lateinit var pageApiService: PageApiService
    private lateinit var viewHandler: ViewHandler
    private var dailyColor = "#fff1e6"
    private var diaryId: Int? = null
    private var uri: Uri? = null

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

    fun init() {
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
                println(dto!!.diaries?.findLast { it.id == diaryId }?.chamyeoUsers!!)
//                val nextUser = binding.innerPageNextUserCharacter
//                CharacterViewer(
//                    this,
//                    nextUser,
//                    Character(
//                        body!!,
//                        bodyColor!!,
//                        blushColor!!,
//                        item!!
//                    )
//                ).show()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        })

        UserResponseSingleton.getMeResponseDto.observe(this, Observer { dto ->
            try {
                val (_, _, body, bodyColor, blushColor, item) = dto!!.user!!
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

    // TODO 버튼과 연결

//        binding.button.setOnClickListener {
//            TedBottomPicker.with(this@CreatePageActivity)
//                .show {
//                    uri = it
//                }
//        }
    }

    fun updateBackground() {
        binding.pageBackgroundColor.setBackgroundColor(Color.parseColor(Setting.backgroundColor))

        if (Setting.page == 0) {
            binding.monoonBackground.visibility = View.INVISIBLE
        } else {
            binding.monoonBackground.visibility = View.VISIBLE
        }
    }

    fun bind() {
        binding.innerPageDailyColor.setOnClickListener {
            val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
                .setInitialColor(Color.parseColor(dailyColor))
                .setColorModel(ColorModel.RGB)
                .setColorModelSwitchEnabled(true)
                .setButtonOkText(android.R.string.ok)
                .setButtonCancelText(android.R.string.cancel)
                .onColorSelected { color: Int ->
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

            var file: File? = null
            if (uri != null) {
                file = File(uri!!.path)
            }

            var img: MultipartBody.Part? = null
            if (file != null) {
                img = MultipartBody.Part.createFormData(
                    "img",
                    file!!.getName(),
                    RequestBody.create(MediaType.parse("image/*"), file)
                )
            }

            pageApiService.createPage(
                MultipartBody.Part.createFormData("diaryId", diaryId!!.toString()),
                MultipartBody.Part.createFormData("title", pageTitle.toString()),
                MultipartBody.Part.createFormData("body", body.toString()),
                MultipartBody.Part.createFormData("color", color),
                img,
                success = { dto ->
                    try {
                        if (dto!!.login != null && dto.login === false) {
                            throw Throwable()
                        }
                        finish()
                    } catch (e: Throwable) {
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