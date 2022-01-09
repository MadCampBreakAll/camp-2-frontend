package com.example.myapplication.ui.diary.create

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.api.friend.dto.PendingFriendDto
import com.example.myapplication.databinding.ActivityCreateDiaryAddFriendPopupBinding
import com.example.myapplication.databinding.CreateDiaryAddingFriendItemBinding
import com.example.myapplication.databinding.DiaryBinding
import com.example.myapplication.databinding.PendingFriendBinding
import com.example.myapplication.ui.friend.PendingFriendsAdapter
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.google.gson.annotations.SerializedName

class CreateDiaryFriendListAdapter(
    private val context: Context,
    private val onSelected: (FriendDto) -> Unit,
    private val callback: () -> Unit,
): RecyclerView.Adapter<CreateDiaryFriendListAdapter.ViewHolder>() {

    private var friendList = mutableListOf<FriendDto>()

    fun addFriend(friend: FriendDto){
        friendList.add(friend)
    }

    fun clear(){
        friendList.clear()
    }

    fun addAllFriend(friend: List<FriendDto>){
        friendList.addAll(friend)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreateDiaryFriendListAdapter.ViewHolder {
        val friendBinding = CreateDiaryAddingFriendItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(friendBinding)
    }

    override fun getItemCount(): Int = friendList.size

    override fun onBindViewHolder(holder: CreateDiaryFriendListAdapter.ViewHolder, position: Int) {
        holder.bind(friendList[position])
    }

    inner class ViewHolder(private val binding: CreateDiaryAddingFriendItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: FriendDto){
            val (_, nickname, body, bodyColor, blushColor, item) = friend
            binding.createDiaryFriendNickName.text = nickname

            val character = Character(
                body,
                bodyColor,
                blushColor,
                item
            )

            CharacterViewer(
                context,
                binding.createDiaryFriendIcon,
                character = character,
            ).show()

            binding.frameLayout.setOnClickListener{
                onSelected(friend)
                callback()
            }
        }
    }

}