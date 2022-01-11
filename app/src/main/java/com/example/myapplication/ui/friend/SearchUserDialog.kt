package com.example.myapplication.ui.friend

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.api.friend.dto.*
import com.example.myapplication.databinding.ActivityInvitedFriendBinding
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class SearchUserDialog(
    private val _context: Context,
    private val viewHandler: ViewHandler,
    private val updateView: () -> Unit
) : Dialog(_context) {

    private lateinit var binding: ActivityInvitedFriendBinding
    private lateinit var friendApiService: FriendApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        init()
        bind()
    }

    private fun init() {
        friendApiService = FriendApiService(TokenManager(_context))
        binding = ActivityInvitedFriendBinding.inflate(layoutInflater)
    }

    private fun bind() {
        setContentView(binding.root)
        binding.sendFriendButton.setOnClickListener {
            val dto = SearchUserFriendWithNicknameRequestDto(
                nickname = binding.inviteFriendName.text.toString()
            )
            friendApiService.searchUserFriendWithNickname(
                dto,
                success = searchUserHandler,
                fail = null
            )
            dismiss()
        }
    }

    private val searchUserHandler : (SearchUserFriendWithNicknameResponseDto?) ->  Unit = handler@{ dto ->
        try {
            if(dto?.status == false){
                Toast.makeText(context, "존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@handler
            }
            AlertDialog.Builder(context)
                .setTitle("${dto!!.user!!.nickname}에게 친구 신청을 보내겠습니까?")
                .setPositiveButton("수락") { dialog, i ->
                    val makeFriendRequestDto = MakeFriendRequestDto(
                        dto?.user?.id!!
                    )
                    friendApiService.makeFriend(
                        makeFriendRequestDto,
                        success = makeFriendHandler,
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

    private val makeFriendHandler : (MakeFriendResponseDto?) -> Unit = handler@{dto ->
            try {
                if(dto?.login != null && dto.login == false) {
                    throw Throwable()
                }
                if(!dto!!.status!!){
                    Toast.makeText(context, "이미 친구입니다.", Toast.LENGTH_SHORT).show()
                    return@handler
                }
                Toast.makeText(context, "친구 요청을 보냈습니다.", Toast.LENGTH_SHORT).show()
                updateView()
            } catch (e: Throwable) {
                viewHandler.goLoginActivityAndRemoveTokens()
                e.printStackTrace()
            }
    }
}