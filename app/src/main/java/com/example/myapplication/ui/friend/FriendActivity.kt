package com.example.myapplication.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.databinding.ActivityFriendBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding
    private lateinit var friendApiService: FriendApiService
    private lateinit var viewHandler: ViewHandler;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()

        friendApiService.getFriends(success = {
                dto ->
            run {
            }
        }, fail = null)

    }

    private fun init(){
        viewHandler = ViewHandler(this);
        binding = ActivityFriendBinding.inflate(layoutInflater);
        friendApiService = FriendApiService(TokenManager(this));
    }

    private fun bind(){
        setContentView(binding.root);
        binding.goPendingFriendActivity.setOnClickListener{
            viewHandler.goPendingFriendActivity()
        }
    }
}