package com.example.myapplication.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
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
            requestFriend(searchUserHandler)
        }
        binding.friends.layoutManager = GridLayoutManager(this, 2)
        binding.friends.adapter = friendsAdapter

        binding.pendingFriends.layoutManager = LinearLayoutManager(this)
        binding.pendingFriends.adapter = pendingFriendAdapter
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

    private val searchUserHandler : (SearchUserFriendWithNicknameResponseDto?) ->  Unit = handler@{ dto ->
        try {
            if(dto?.status == false){
                Toast.makeText(this, "존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@handler
            }

            AlertDialog.Builder(this)
                .setTitle("${dto!!.user!!.nickname}에게 친구 신청을 보내겠습니까?")
                .setPositiveButton("수락") { dialog, i ->
                    val makeFriendRequestDto = MakeFriendRequestDto(
                        dto?.user?.id!!
                    )
                    friendApiService.makeFriend(
                        makeFriendRequestDto,
                        success = handler@{ responseDto ->
                            try {
                                if(responseDto?.login != null && responseDto.login == false) {
                                    throw Error()
                                }
                                if(!responseDto!!.status!!){
                                    Toast.makeText(this, "이미 친구입니다.", Toast.LENGTH_SHORT).show()
                                    return@handler
                                }
                                Toast.makeText(this, "친구 요청을 보냈습니다.", Toast.LENGTH_SHORT).show()
                            } catch (e: Throwable) {
                                viewHandler.goLoginActivityAndRemoveTokens()
                            }
                        },
                        fail = null
                    )
                }
                .setNegativeButton("취소") { dialog, i ->
                    dialog.dismiss()
                }
                .create()
                .show()
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

    private fun requestFriend(callback: (SearchUserFriendWithNicknameResponseDto?) -> Unit) {
        var dialog = PendingFriendActivity(this, callback)
        dialog.show()
        dialog.window?.setLayout(750, 650)
    }

}