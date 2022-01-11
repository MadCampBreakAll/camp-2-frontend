package com.example.myapplication.ui.page

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.ui.diary.create.CreateDiary

class PageGridDialog(
    private val _context: Context,
    private val callback: () -> Unit
) :
    Dialog(_context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_grid_dialog)
    }
}