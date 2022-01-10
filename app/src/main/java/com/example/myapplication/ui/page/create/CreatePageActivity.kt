package com.example.myapplication.ui.page.create

import android.graphics.Color
import android.content.Context
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

class CreatePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePageBinding
    private var dailyColor = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        binding.innerPageDailyColor.setOnClickListener{
            val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
                .setInitialColor(Color.parseColor("#fff1e6"))
                .setColorModel(ColorModel.RGB)
                .setColorModelSwitchEnabled(true)
                .setButtonOkText(android.R.string.ok)
                .setButtonCancelText(android.R.string.cancel)
                .onColorSelected{ color: Int ->
                    dailyColor = "#$color"
                    binding.innerPageDailyColor.setColorFilter(color)
                }
                .create()

            colorPicker.show(supportFragmentManager, "color_picker")

        }
    }

    fun init(){
        binding = ActivityCreatePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun bind(){

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