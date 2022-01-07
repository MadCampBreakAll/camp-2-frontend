package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.api.dto.DiaryDto
import com.example.myapplication.api.dto.PageDto
import com.example.myapplication.databinding.ActivityDiaryInnerBinding
import com.example.myapplication.databinding.ActivityMainBinding

// Diary의 속지(페이지들을 볼 수 있는 곳)를 보는 화면 -> 속지들은 viewpager로 표현된다
// button의 setonclicklistner은 DiaryCoverAdapter 안에 있다. -> diaryList를 가지고 있어 diary 정보들을 모두 알고 있다.
// Bundle을 통해서 diary의 id를 알 수 있도록 한다. -> 이 정보로 원하는 것들을 activity에서 얻어내자
// button은 diary가 가지고 있는 정보에서

class DiaryInnerActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDiaryInnerBinding
    private val binding get() = _binding!!

    private lateinit var pageLetterAdapter: PageLetterViewPageAdapter
    var pageList = mutableListOf<PageDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDiaryInnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var diaryId = intent.getStringExtra("diaryId")
    }
}