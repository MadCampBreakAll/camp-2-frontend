package com.example.myapplication.ui.page

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.api.page.dto.PageDto
import com.example.myapplication.databinding.ActivityPageGridDialogBinding
import com.example.myapplication.ui.singleton.PageResponseSingleton

class AllPageDialog(
    private val _context: Context,
    private val diaryId: Int,
    private val lifecycle: LifecycleOwner
) : Dialog(_context){
    private lateinit var binding: ActivityPageGridDialogBinding
    private lateinit var adapter: GridPageAdapter
    private lateinit var pages: List<PageDto>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        init()
        bind()
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun init() {
        binding = ActivityPageGridDialogBinding.inflate(layoutInflater)
        adapter = GridPageAdapter(context)
        binding.pages.adapter = adapter

        PageResponseSingleton.getDiaryInnerPagesResponse.observe(lifecycle, {
            if(it!!.diary.id != this.diaryId)  {
                return@observe
            }
            adapter.clear()
            adapter.addPage(it.pages)
            adapter.notifyDataSetChanged()
            binding.gridViewTitle.text = it!!.diary.title
        })
    }

    private fun bind() {
        setContentView(binding.root)
    }

}