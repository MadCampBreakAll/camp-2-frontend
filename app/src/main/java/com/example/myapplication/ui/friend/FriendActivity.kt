package com.example.myapplication.ui.friend

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    private lateinit var requestsFriendAdapter: RequestsFriendsAdapter

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
        requestsFriendAdapter = RequestsFriendsAdapter(
            this,
            this,
            accept = acceptPendingFriendHandler,
            reject = rejectPendingFriendHandler,
        )
    }

    private fun bind(){
        setContentView(binding.root);
        binding.goPendingFriendActivity.setOnClickListener{
            createSearchUserDialog()
        }
        binding.friends.layoutManager = GridLayoutManager(this, 2)
        binding.friends.adapter = friendsAdapter
        binding.pendingFriends.layoutManager = GridLayoutManager(this, 2)
        binding.pendingFriends.adapter = requestsFriendAdapter
        binding.root.setOnRefreshListener {
            update()
            binding.root.isRefreshing = false
        }
    }

    private val update : () -> Unit = {
        updateFriends()
        updateRequestFriends()
    }

    private fun updateView(){
        binding.noneFriendRequestText.visibility = View.INVISIBLE
        binding.noneFriendText.visibility = View.INVISIBLE
        if(requestsFriendAdapter.itemCount == 0) {
            binding.noneFriendRequestText.visibility = View.VISIBLE
        }
        if(friendsAdapter.itemCount == 0){
            binding.noneFriendText.visibility = View.VISIBLE
        }
    }

    private fun updateFriends(){
        friendApiService.getFriends(success = getFriendsHandler, fail = null)
    }

    private fun updateRequestFriends(){
        friendApiService.getRequestsFriend(success = getRequestsFriendHandler, fail = null)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val getFriendsHandler : (GetMyFriendsResponseDto?) -> Unit = handler@{ response ->
        try {
            friendsAdapter.clear()
            friendsAdapter.addFriends(response?.friends!!)
            friendsAdapter.notifyDataSetChanged()
            updateView()
            return@handler
        } catch (e : Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
            return@handler
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val getRequestsFriendHandler : (GetRequestsFriendResponseDto?) -> Unit = handler@{ dto ->
        try {
            val pendingFriends = dto!!.pending!!.map {
                RequestsFriend(
                    it.id,
                    it.nickname,
                    it.body,
                    it.bodyColor,
                    it.blushColor,
                    it.item,
                    true
                )
            }
            val askedFriends = dto.asked!!.map {
                RequestsFriend(
                it.id,
                it.nickname,
                it.body,
                it.bodyColor,
                it.blushColor,
                it.item,
                false)
            }

            requestsFriendAdapter.clear()
            requestsFriendAdapter.addRequestFriends(pendingFriends)
            requestsFriendAdapter.addRequestFriends(askedFriends)
            requestsFriendAdapter.notifyDataSetChanged()
            updateView()
            return@handler
        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
            return@handler
        }
    }

    private val acceptPendingFriendHandler : (RequestsFriend) -> Unit = { dto ->
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
                        update()
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

    private val rejectPendingFriendHandler : (RequestsFriend) -> Unit = { dto ->
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
                        update()
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
        var dialog = SearchUserDialog(this, viewHandler, update)
        dialog.show()
        dialog.window?.setLayout(750, 650)
    }
}