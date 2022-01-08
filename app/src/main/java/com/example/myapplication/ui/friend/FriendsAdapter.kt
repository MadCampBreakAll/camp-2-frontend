package com.example.myapplication.ui.friend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.databinding.FriendBinding

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
        val binding = FriendBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friends[position])
    }

    override fun getItemCount(): Int {
        println(friends.size)
        return friends.size
    }

    inner class ViewHolder(private val friendBinding: FriendBinding) : RecyclerView.ViewHolder(friendBinding.root){
        fun bind(friend: FriendDto){
            friendBinding.name.text = friend.nickname
        }
    }
}