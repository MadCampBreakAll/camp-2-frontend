package com.example.myapplication.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.api.friend.dto.SearchUserFriendWithNicknameRequestDto
import com.example.myapplication.databinding.ActivityInvitedFriendBinding
import com.example.myapplication.util.TokenManager

class PendingFriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInvitedFriendBinding
    private lateinit var friendApiService: FriendApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()

        friendApiService.getPendingFriend(
            success = { dto ->

            },
            fail = null
        )
    }

    fun init(){
        friendApiService = FriendApiService(TokenManager(this))
        binding = ActivityInvitedFriendBinding.inflate(layoutInflater)
    }

    fun bind(){
        setContentView(binding.root);
        binding.sendFriendButton.setOnClickListener{
            val dto = SearchUserFriendWithNicknameRequestDto(
                nickname = binding.inviteFriendName.text.toString()
            )
            friendApiService.searchUserFriendWithNickname(
                dto,
                success = { dto ->

                },
                fail = null
            )
        }
    }

}