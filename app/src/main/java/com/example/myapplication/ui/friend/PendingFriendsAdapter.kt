package com.example.myapplication.ui.friend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.api.friend.dto.PendingFriendDto
import com.example.myapplication.databinding.FriendBinding
import com.example.myapplication.databinding.PendingFriendBinding

class PendingFriendsAdapter(private val context: Context) : RecyclerView.Adapter<PendingFriendsAdapter.ViewHolder>()
{

    private var pendingFriends = mutableListOf<PendingFriendDto>()

    fun addPendingFriend(pendingFriend: PendingFriendDto){
        pendingFriends.add(pendingFriend)
    }

    fun addPendingFriends(friend: List<PendingFriendDto>) {
        pendingFriends.addAll(friend)
    }

    fun clear(){
        pendingFriends.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PendingFriendBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pendingFriends[position])
    }

    override fun getItemCount(): Int {
        return pendingFriends.size
    }

    inner class ViewHolder(private val pendingFriendBinding: PendingFriendBinding) : RecyclerView.ViewHolder(pendingFriendBinding.root){
        fun bind(friend: PendingFriendDto){
            pendingFriendBinding.name.text = friend.nickname
        }
    }
}