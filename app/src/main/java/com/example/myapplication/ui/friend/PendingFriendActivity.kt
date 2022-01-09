package com.example.myapplication.ui.friend

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

class PendingFriendActivity(
    private val _context: Context,
    private val callback: (SearchUserFriendWithNicknameResponseDto?) -> Unit
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
                success = callback,
                fail = null
            )
            dismiss()
        }
    }

//    private val searchUserHandler: (SearchUserFriendWithNicknameResponseDto?) -> Unit =
//        handler@{ dto ->
//            try {
//                if (dto?.status == false) {
//                    Toast.makeText(this, "존재하지 않습니다.", Toast.LENGTH_SHORT).show()
//                    return@handler
//                }
//
//                AlertDialog.Builder(_context)
//                    .setTitle("${dto!!.user!!.nickname}을 친구 추가 하시겠습니까?")
//                    .setPositiveButton("수락") { dialog, i ->
//                        val makeFriendRequestDto = MakeFriendRequestDto(
//                            dto?.user?.id!!
//                        )
//                        friendApiService.makeFriend(
//                            makeFriendRequestDto,
//                            success = handler@{ responseDto ->
//                                try {
//                                    if (responseDto?.login != null && responseDto.login == false) {
//                                        throw Error()
//                                    }
//                                    if (!responseDto!!.status!!) {
//                                        Toast.makeText(this, "이미 친구를 추가한 상태이거나", Toast.LENGTH_SHORT)
//                                            .show()
//                                        return@handler
//                                    }
//                                    Toast.makeText(this, "친구 추가에 성공하셨습니다", Toast.LENGTH_SHORT)
//                                        .show()
//                                } catch (e: Throwable) {
//                                    viewHandler.goLoginActivityAndRemoveTokens()
//                                }
//                            },
//                            fail = null
//                        )
//                    }
//                    .setNegativeButton("취소") { dialog, i ->
//                        dialog.dismiss()
//                    }
//                    .create()
//                    .show()
//            } catch (e: Throwable) {
//                viewHandler.goLoginActivityAndRemoveTokens()
//            }
//        }
//
//
//    private val acceptPendingFriendHandler: (PendingFriendDto) -> Unit = { dto ->
//        try {
//            val requestDto = AcceptFriendRequestDto(
//                dto.id,
//                accept = true
//            )
//            friendApiService.acceptFriend(
//                requestDto,
//                success = { responseDto ->
//                    try {
//                        if (!responseDto?.status!!) {
//                            throw Error()
//                        }
//                        Toast.makeText(this, "${dto.nickname}님을 친구 추가 하셨습니다.", Toast.LENGTH_SHORT)
//                            .show()
//                    } catch (e: Throwable) {
//                        viewHandler.goLoginActivityAndRemoveTokens()
//                    }
//                },
//                fail = null
//            )
//        } catch (e: Throwable) {
//            viewHandler.goLoginActivityAndRemoveTokens()
//        }
//    }
//
//    private val rejectPendingFriendHandler: (PendingFriendDto) -> Unit = { dto ->
//        try {
//            val requestDto = AcceptFriendRequestDto(
//                dto.id,
//                accept = false
//            )
//            friendApiService.acceptFriend(
//                requestDto,
//                success = { responseDto ->
//                    try {
//                        if (!responseDto?.status!!) {
//                            throw Error()
//                        }
//                        Toast.makeText(this, "${dto.nickname}님을 친구 거절 하셨습니다.", Toast.LENGTH_SHORT)
//                            .show()
//                    } catch (e: Throwable) {
//                        viewHandler.goLoginActivityAndRemoveTokens()
//                    }
//                },
//                fail = null
//            )
//        } catch (e: Throwable) {
//            viewHandler.goLoginActivityAndRemoveTokens()
//        }
//    }

}