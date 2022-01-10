package com.example.myapplication.ui.friend

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.trace
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.api.friend.dto.RequestsFriendDto
import com.example.myapplication.databinding.AskedFriendBinding
import com.example.myapplication.databinding.EmptyFriendBinding
import com.example.myapplication.databinding.FragmentCharacterItemBinding
import com.example.myapplication.databinding.PendingFriendBinding
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer

class RequestsFriendsAdapter(
    private val activity: Activity,
    private val context: Context,
    private val accept: (RequestsFriend) -> Unit,
    private val reject: (RequestsFriend) -> Unit
) : RecyclerView.Adapter<RequestsFriendsAdapter.RequestFriendViewHolder>()
{
    private var requestsFriends = mutableListOf<RequestsFriend>()
    fun addRequestFriends(requestsFriends: RequestsFriend){
        this.requestsFriends.add(requestsFriends)
    }

    fun addRequestFriends(friend: List<RequestsFriend>) {
        requestsFriends.addAll(friend)
    }

    fun clear(){
        requestsFriends.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestFriendViewHolder {
        if(viewType == 0) {
            return PendingViewHolder(
                PendingFriendBinding.inflate(LayoutInflater.from(activity), parent, false)
            )
        }
        return AskedViewHolder(
            AskedFriendBinding.inflate(LayoutInflater.from(activity), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        if(this.requestsFriends[position].pending){
            return 0
        }
        return 1
    }


    override fun onBindViewHolder(holder: RequestFriendViewHolder, position: Int) {
        holder.bind(requestsFriends[position])
    }

    override fun getItemCount(): Int {
        return requestsFriends.size
    }

    inner class PendingViewHolder(private val binding: PendingFriendBinding) : RequestFriendViewHolder(binding){
        override fun bind(friend: RequestsFriend){
            binding.accept.setOnClickListener{
                accept(friend)
            }
            binding.reject.setOnClickListener{
                reject(friend)
            }
            val user = binding.requestFriendInfo.createDiaryFriendIcon
            binding.requestFriendInfo.createDiaryFriendNickName.text = friend.nickname
            CharacterViewer(
                context,
                user,
                Character(
                    friend.body,
                    friend.bodyColor,
                    friend.blushColor,
                    friend.item
                )
            ).show()
        }
    }

    inner class AskedViewHolder(private val binding: AskedFriendBinding) : RequestFriendViewHolder(binding){
        override fun bind(friend: RequestsFriend){
            binding.requestFriendInfo.createDiaryFriendNickName.text = friend.nickname
            val user = binding.requestFriendInfo.createDiaryFriendIcon
            CharacterViewer(
                context,
                user,
                Character(
                    friend.body,
                    friend.bodyColor,
                    friend.blushColor,
                    friend.item
                )
            ).show()
        }
    }

    inner abstract class RequestFriendViewHolder(private val binding:ViewBinding) : RecyclerView.ViewHolder(binding.root){
        abstract fun bind(friend: RequestsFriend)
    }
}