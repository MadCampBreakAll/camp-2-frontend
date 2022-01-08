package com.example.myapplication.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.databinding.ActivityFriendBinding
import com.example.myapplication.util.TokenManager

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding
    private lateinit var friendApiService: FriendApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        init()

        friendApiService.getFriends(success = {
                dto ->
            run {
            }
        }, fail = null)

        friendApiService.getPendingFriend(success = {
            dto -> run {}
        }, fail = null);

    }

    private fun init(){
        friendApiService = FriendApiService(TokenManager(this));
    }

    private fun bind(){
        binding = ActivityFriendBinding.inflate(layoutInflater);
        setContentView(binding.root);
    }
}