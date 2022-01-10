package com.example.myapplication.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.api.friend.dto.*
import com.example.myapplication.databinding.ActivityFriendBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding
    private lateinit var friendApiService: FriendApiService
    private lateinit var viewHandler: ViewHandler
    private lateinit var friendsAdapter: FriendsAdapter
    private lateinit var pendingFriendAdapter: PendingFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        update()
    }

    private fun init(){
        viewHandler = ViewHandler(this)
        binding = ActivityFriendBinding.inflate(layoutInflater)
        friendApiService = FriendApiService(TokenManager(this))
        friendsAdapter = FriendsAdapter(this)
        pendingFriendAdapter = PendingFriendsAdapter(
            this,
            accept = acceptPendingFriendHandler,
            reject = rejectPendingFriendHandler
        )
    }

    private fun bind(){
        setContentView(binding.root);
        binding.goPendingFriendActivity.setOnClickListener{
            createSearchUserDialog()
        }
        binding.friends.layoutManager = GridLayoutManager(this, 2)
        binding.friends.adapter = friendsAdapter
        if(friendsAdapter.itemCount == 0){
            binding.noneFriendText.visibility = View.VISIBLE
        }

        binding.pendingFriends.layoutManager = LinearLayoutManager(this)
        binding.pendingFriends.adapter = pendingFriendAdapter
        if(pendingFriendAdapter.itemCount == 0) {
            binding.noneFriendRequestText.visibility = View.VISIBLE
        }
    }

    private fun update(){
        updateFriends()
        updatePendingFriends()
    }

    private fun updateFriends(){
        friendApiService.getFriends(success = getFriendsHandler, fail = null)
    }

    private fun updatePendingFriends(){
        friendApiService.getPendingFriend(success = getPendingFriendsHandler, fail = null)
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

    private val getPendingFriendsHandler : (GetPendingFriendResponseDto?) -> Unit = handler@{ response ->
        try {

            return@handler
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
            return@handler
        }
    }

    private val acceptPendingFriendHandler : (PendingFriendDto) -> Unit = { dto ->
        try {
            val requestDto = AcceptFriendRequestDto(
                dto.id,
                accept = true
            )
            friendApiService.acceptFriend(
                requestDto,
                success = { responseDto ->
                    try {
                        if(!responseDto?.status!!){
                            throw Error()
                        }
                        Toast.makeText(this, "${dto.nickname}님을 친구 추가 하셨습니다.", Toast.LENGTH_SHORT).show()
                    } catch (e : Throwable){
                        viewHandler.goLoginActivityAndRemoveTokens()
                    }
                },
                fail = null
            )
        } catch (e: Throwable){
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

    private val rejectPendingFriendHandler : (PendingFriendDto) -> Unit = { dto ->
        try {
            val requestDto = AcceptFriendRequestDto(
                dto.id,
                accept = false
            )
            friendApiService.acceptFriend(
                requestDto,
                success = {responseDto ->
                    try {
                        if(!responseDto?.status!!){
                            throw Error()
                        }
                        Toast.makeText(this, "${dto.nickname}님의 요청을 거절했습니다.", Toast.LENGTH_SHORT).show()
                    } catch (e: Throwable) {
                        viewHandler.goLoginActivityAndRemoveTokens()
                    }
                },
                fail = null
            )
        } catch (e: Throwable){
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }



    private fun createSearchUserDialog() {
        var dialog = SearchUserDialog(this, viewHandler)
        dialog.show()
        dialog.window?.setLayout(750, 650)
    }

}