package com.example.myapplication.ui.page.create

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreatePageBinding
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog

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
}