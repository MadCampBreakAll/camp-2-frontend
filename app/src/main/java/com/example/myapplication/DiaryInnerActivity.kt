package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.api.dto.DiaryDto
import com.example.myapplication.api.dto.PageDto
import com.example.myapplication.databinding.ActivityDiaryInnerBinding
import com.example.myapplication.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.time.LocalDate

// Diary의 속지(페이지들을 볼 수 있는 곳)를 보는 화면 -> 속지들은 viewpager로 표현된다
// button의 setonclicklistner은 DiaryCoverAdapter 안에 있다. -> diaryList를 가지고 있어 diary 정보들을 모두 알고 있다.
// putExtra을 통해서 diary의 id를 알 수 있도록 한다. -> 이 정보로 원하는 것들을 activity에서 얻어내자
// 이 activity에서는 diary_id를 통해 page들의 정보를 알 수 있어야 한다.

class DiaryInnerActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDiaryInnerBinding
    private val binding get() = _binding!!
    private lateinit var pageLetterViewPageAdapter: PageLetterViewPageAdapter

    private lateinit var pageLetterAdapter: PageLetterViewPageAdapter
    var pageList = mutableListOf<PageDto>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDiaryInnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var diaryId = intent.getStringExtra("diaryId")

        // 이 diaryId를 이용해서 page들의 정보들을 얻어내야 한다.
        pageLetterViewPageAdapter = PageLetterViewPageAdapter(this)
        binding.pagesLetterViewPager.adapter = pageLetterViewPageAdapter

        for (i in 1..3){
            pageList.add(
                PageDto("12345"
                    ,"12827"
                    ,"18374"
                    , 12
                    , "기분 최고!"
                    , "오늘은 많은 일을 했다. \n기분 최고! \n근데 졸리다!"
                    , LocalDate.now()
                    , "#a5a58d"
                    , null
                )
            )
        }

        pageLetterViewPageAdapter.pageList = pageList

        println("diaryId: ${diaryId}")
    }
}