package com.example.myapplication.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.api.user.dto.GetMyFriendsResponseDto
import com.example.myapplication.databinding.ActivityFriendBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding
    private lateinit var friendApiService: FriendApiService
    private lateinit var viewHandler: ViewHandler
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        updateFriend()
    }

    private fun init(){
        viewHandler = ViewHandler(this)
        binding = ActivityFriendBinding.inflate(layoutInflater)
        friendApiService = FriendApiService(TokenManager(this))
        friendsAdapter = FriendsAdapter(this)
    }

    private fun bind(){
        setContentView(binding.root);
        binding.goPendingFriendActivity.setOnClickListener{
            viewHandler.goPendingFriendActivity()
        }
        binding.friends.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.friends.adapter = friendsAdapter
    }

    fun updateFriend(){
        friendApiService.getFriends(success = getFriendsHandler, fail = null)
    }

    private val getFriendsHandler : (GetMyFriendsResponseDto?) -> Unit = handler@{ response ->
        try {
            friendsAdapter.clear()
            friendsAdapter.addFriends(response?.friends!!)
            friendsAdapter.notifyDataSetChanged()
            return@handler
        } catch (e : Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
            return@handler
        }

    }
}