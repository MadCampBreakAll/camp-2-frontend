package com.example.myapplication.ui.diary.create

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.databinding.ActivityCreateDiaryAddFriendPopupBinding

class CreateDiaryAddFriendPopupActivity(
    private val _context: Context,
    private val createDiary: CreateDiary,
    private val friendId: Int,
    private val callback: () -> Unit) :
    Dialog(_context) {

    private lateinit var binding: ActivityCreateDiaryAddFriendPopupBinding
    private lateinit var adapter: CreateDiaryFriendListAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        update()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun init() {
        binding = ActivityCreateDiaryAddFriendPopupBinding.inflate(layoutInflater)
        adapter = CreateDiaryFriendListAdapter(_context, onSelectedHandler, callback)
        adapter.addAllFriend(createDiary.getCanSelectedFriends())
    }

    private fun bind() {
        setContentView(binding.root)
        binding.createDiaryFriendListView.adapter = adapter
        binding.createDiaryFriendListView.layoutManager = GridLayoutManager(_context, 2)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun update(){
        adapter.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val onSelectedHandler : (FriendDto) -> Unit = { dto ->
        createDiary.selectFriend(friendId, friendDto = dto )
        dismiss()
    }

}