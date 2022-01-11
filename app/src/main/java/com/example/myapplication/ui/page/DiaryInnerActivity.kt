package com.example.myapplication.ui.page

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.page.PageApiService
import com.example.myapplication.databinding.ActivityDiaryInnerBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer2

import android.graphics.Color
import androidx.lifecycle.Observer
import com.example.myapplication.ui.main.Setting
import com.example.myapplication.ui.singleton.DiaryResponseSingleton
import com.example.myapplication.ui.singleton.PageResponseSingleton

class DiaryInnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryInnerBinding
    private lateinit var pageLetterViewPageAdapter: PageLetterViewPageAdapter
    private lateinit var tokenManager: TokenManager
    private lateinit var diaryApiService: DiaryApiService
    private lateinit var pageApiService: PageApiService
    private lateinit var viewHandler: ViewHandler
    private var diaryId: Int? = null
    private var userId: Int? = null

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
        pageLetterViewPageAdapter = PageLetterViewPageAdapter(this, supportFragmentManager)
        binding.pagesLetterViewPager.adapter = pageLetterViewPageAdapter
        var bookFlipPageTransformer = BookFlipPageTransformer2()

        bookFlipPageTransformer.isEnableScale = true
        bookFlipPageTransformer.scaleAmountPercent = 10f

        binding.pagesLetterViewPager.setPageTransformer(bookFlipPageTransformer)

        try {
            diaryId = intent.getIntExtra("diary_id", -1)
            userId = intent.getIntExtra("user_id", -1)
        } catch (e: Throwable){
            Log.d("DEBUG", e.toString())
            finish()
        }

         Setting.setting.observe(this, Observer { setting ->
            updateBackground()
         })

        binding.root.setOnRefreshListener {
            update()
            binding.root.isRefreshing = false
        }

    }

    fun updateBackground() {
        binding.blankPageBackground.setBackgroundColor(Color.parseColor(Setting.backgroundColor))

        if(Setting.page == 0) {
            binding.monoonBackground.visibility = View.INVISIBLE
        }
        else {
            binding.monoonBackground.visibility = View.VISIBLE
        }
    }

    private fun bind(){
        setContentView(binding.root)
        binding.pageAddBtn.setOnClickListener {
            viewHandler.goCreatePageAcitivty(diaryId?:-1)
        }
        binding.goGridView.setOnClickListener{
            val dialog = AllPageDialog(this, diaryId!!, this, {
                val pages = PageResponseSingleton.getDiaryInnerPagesResponse.value?.pages!!
                for(i: Int in pages.indices) {
                    if(pages[i].id == it){
                        binding.pagesLetterViewPager.currentItem = i
                    }
                }
            })
            dialog.window?.setLayout(800, 1200)
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        update()
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
                    updateView(dto.diary.nextUser.id!!)
                } catch (e: Throwable) {
                    viewHandler.goLoginActivityAndRemoveTokens()
                }
            },
            fail = null
        )
    }

    private fun updateView(nextUserId: Int){
        binding.pageAddBtn.visibility = View.INVISIBLE
        if(userId == nextUserId){
            binding.pageAddBtn.visibility = View.VISIBLE
        }
    }
}