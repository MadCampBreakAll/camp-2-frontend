package com.example.myapplication.ui.friend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.databinding.CreateDiaryAddingFriendItemBinding
import com.example.myapplication.databinding.UserCharacterBinding
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer

class FriendsAdapter(private val context: Context) : RecyclerView.Adapter<FriendsAdapter.ViewHolder>()
{

    private var friends = mutableListOf<FriendDto>()

    fun addFriend(friend: FriendDto){
        friends.add(friend)
    }

    fun addFriends(friend: List<FriendDto>) {
        friends.addAll(friend)
    }

    fun clear(){
        friends.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CreateDiaryAddingFriendItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friends[position])
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    inner class ViewHolder(private val binding: CreateDiaryAddingFriendItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(friend: FriendDto){
            val (_, nickname, body, bodyColor, blushColor, item) = friend
            try {
                CharacterViewer(
                    context,
                    binding.createDiaryFriendIcon,
                    Character(
                        body,
                        bodyColor,
                        blushColor,
                        item
                    )
                ).show()
                binding.createDiaryFriendNickName.text = nickname
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}