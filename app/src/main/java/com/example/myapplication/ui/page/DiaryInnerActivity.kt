package com.example.myapplication.ui.page

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.api.page.dto.PageDto
import com.example.myapplication.databinding.ActivityDiaryInnerBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

// Diary의 속지(페이지들을 볼 수 있는 곳)를 보는 화면 -> 속지들은 viewpager로 표현된다
// button의 setonclicklistner은 DiaryCoverAdapter 안에 있다. -> diaryList를 가지고 있어 diary 정보들을 모두 알고 있다.
// putExtra을 통해서 diary의 id를 알 수 있도록 한다. -> 이 정보로 원하는 것들을 activity에서 얻어내자
// 이 activity에서는 diary_id를 통해 page들의 정보를 알 수 있어야 한다.

class DiaryInnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryInnerBinding
    private lateinit var pageLetterViewPageAdapter: PageLetterViewPageAdapter
    private lateinit var tokenManager: TokenManager
    private lateinit var diaryApiService: DiaryApiService
    private lateinit var pageApiService: PageApiService
    private lateinit var viewHandler: ViewHandler
    private var diaryId: Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        update()
    }

    private fun init(){
        binding = ActivityDiaryInnerBinding.inflate(layoutInflater)
        viewHandler = ViewHandler(this)
        tokenManager = TokenManager(this)
        diaryApiService = DiaryApiService(tokenManager)
        pageApiService = PageApiService(tokenManager)
        pageLetterViewPageAdapter = PageLetterViewPageAdapter(this)
        binding.pagesLetterViewPager.adapter = pageLetterViewPageAdapter

        try {
            diaryId = intent.getStringExtra(resources.getString(R.string.diary_id))!!.toInt()
        } catch (e: Throwable){
            Log.d("DEBUG", e.toString())
            finish()
        }
    }

    private fun bind(){
        setContentView(binding.root)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun update(){
        pageApiService.getDiaryInnerPages(
            diaryId?:-1,
            success = {
                    dto ->
                try {
                    if(!dto!!.status!!){
                        throw Throwable()
                    }
                    pageLetterViewPageAdapter.clear()
                    pageLetterViewPageAdapter.addAllPage(dto.pages)
                    pageLetterViewPageAdapter.notifyDataSetChanged()
                } catch (e: Throwable) {
                    viewHandler.goLoginActivityAndRemoveTokens()
                }
            },
            fail = null
        )
    }
}