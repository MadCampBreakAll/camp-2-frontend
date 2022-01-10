package com.example.myapplication.ui.page.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreatePageBinding

class CreatePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init(){
        binding = ActivityCreatePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun bind(){

    }
}