package com.example.myapplication.ui.setting.backfont

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySettingBackgroundBinding

class SettingBackgroundActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBackgroundBinding
    private lateinit var backgroundColorAdapter: BackgroundColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBackgroundBinding.inflate(layoutInflater)
        backgroundColorAdapter = BackgroundColorAdapter(this, setColor())

        setMonoon()
        binding.colorGrid.layoutManager = GridLayoutManager(this, 3)
        binding.colorGrid.adapter=backgroundColorAdapter

        setContentView(binding.root)
    }

    fun setMonoon(){
        binding.monoonInnerPage.setOnClickListener{
            binding.monoonBackground.visibility = View.VISIBLE
        }

        binding.normalInnerPage.setOnClickListener{
            binding.monoonBackground.visibility = View.INVISIBLE
        }
    }

    fun setColor() = { color: String ->
        binding.testingViewColor.setBackgroundColor(Color.parseColor(color))
    }
}