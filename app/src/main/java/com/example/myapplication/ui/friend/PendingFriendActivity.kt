package com.example.myapplication.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.api.friend.dto.SearchUserFriendWithNicknameRequestDto
import com.example.myapplication.api.friend.dto.SearchUserFriendWithNicknameResponseDto
import com.example.myapplication.api.user.dto.GetPendingFriendResponseDto
import com.example.myapplication.databinding.ActivityInvitedFriendBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class PendingFriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInvitedFriendBinding
    private lateinit var friendApiService: FriendApiService
    private lateinit var viewHandler: ViewHandler
    private lateinit var pendingFriendsAdapter: PendingFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        updatePendingFriends()
    }

    private fun init(){
        friendApiService = FriendApiService(TokenManager(this))
        binding = ActivityInvitedFriendBinding.inflate(layoutInflater)
        viewHandler = ViewHandler(this)
        pendingFriendsAdapter = PendingFriendsAdapter(this)
        binding.pendingFriends.adapter = pendingFriendsAdapter
        binding.pendingFriends.layoutManager = LinearLayoutManager(this)
    }

    private fun bind(){
        setContentView(binding.root)
        binding.sendFriendButton.setOnClickListener{
            val dto = SearchUserFriendWithNicknameRequestDto(
                nickname = binding.inviteFriendName.text.toString()
            )
            friendApiService.searchUserFriendWithNickname(
                dto,
                success = searchUserHandler,
                fail = null
            )
        }
    }

    private val searchUserHandler : (SearchUserFriendWithNicknameResponseDto?) ->  Unit = { dto ->
        try {
            if(dto?.status!!) {
                Toast.makeText(this, dto.user.toString(), Toast.LENGTH_SHORT).show()
            }

            if(dto.status == false){
                Toast.makeText(this, "존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

    private fun updatePendingFriends(){
        friendApiService.getPendingFriend(
            success = getPendingFriendsHandler,
            fail = null
        )
    }

    private val getPendingFriendsHandler: (GetPendingFriendResponseDto?) -> Unit = handler@{ dto ->
        try {
            if(!dto!!.status!!){
                throw Error()
            }

            pendingFriendsAdapter.clear()
            pendingFriendsAdapter.addPendingFriends(dto.users!!)
            pendingFriendsAdapter.notifyDataSetChanged()
            return@handler
        } catch (e: Error) {
            viewHandler.goLoginActivityAndRemoveTokens()
            return@handler
        }
    }

}