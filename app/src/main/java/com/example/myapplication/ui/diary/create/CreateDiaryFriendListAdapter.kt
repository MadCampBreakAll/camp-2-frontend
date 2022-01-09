package com.example.myapplication.ui.diary.create

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.friend.dto.PendingFriendDto
import com.example.myapplication.databinding.ActivityCreateDiaryAddFriendPopupBinding
import com.example.myapplication.databinding.CreateDiaryAddingFriendItemBinding
import com.example.myapplication.databinding.DiaryBinding
import com.example.myapplication.databinding.PendingFriendBinding
import com.example.myapplication.ui.friend.PendingFriendsAdapter

class CreateDiaryFriendListAdapter(
    private val context: Context,
    private val accept: (PendingFriendDto) -> Unit,
    private val reject: (PendingFriendDto) -> Unit
): RecyclerView.Adapter<CreateDiaryFriendListAdapter.ViewHolder>() {

    private var friendList = mutableListOf<CreateDiaryAddingFriendItemBinding>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreateDiaryFriendListAdapter.ViewHolder {
        val friendBinding = CreateDiaryAddingFriendItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(friendBinding)
    }

    override fun getItemCount(): Int = friendList.size

    override fun onBindViewHolder(holder: CreateDiaryFriendListAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: CreateDiaryAddingFriendItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

}