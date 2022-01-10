package com.example.myapplication.ui.diary.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.CreateDiaryResponseDto
import com.example.myapplication.api.friend.FriendApiService
import com.example.myapplication.api.friend.dto.GetMyFriendsResponseDto
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.user.dto.GetMeResponseDto
import com.example.myapplication.databinding.ActivityCreateDiaryBinding
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class CreateDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateDiaryBinding
    private lateinit var diaryApiService: DiaryApiService
    private lateinit var friendApiService: FriendApiService
    private lateinit var userApiService: UserApiService
    private lateinit var tokenManager: TokenManager
    private lateinit var viewHandler: ViewHandler
    private val createDiary: CreateDiary by lazy {
        CreateDiary()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        update()
    }

    private fun init(){
        binding = ActivityCreateDiaryBinding.inflate(layoutInflater)
        tokenManager = TokenManager(this)
        viewHandler = ViewHandler(this)
        diaryApiService = DiaryApiService(tokenManager)
        friendApiService = FriendApiService(tokenManager)
        userApiService = UserApiService(tokenManager)
    }

    private fun bind(){
        setContentView(binding.root);
        binding.createDiaryButton.setOnClickListener {
            try {
                createDiary!!.setTitle(binding.title.text.toString())
                val dto = createDiary!!.toCreateDiaryRequestDto()
                diaryApiService.createDiary(dto, success = createDiaryHandler, fail = null);
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

        binding.secondFriend.setOnClickListener {
            createAddFriendDialog(1, createDiary!!, updateSelectedFriendView)
        }

        binding.thirdFriend.setOnClickListener {
            createAddFriendDialog(2, createDiary!!, updateSelectedFriendView)
        }

        binding.forthFriend.setOnClickListener {
            createAddFriendDialog(3, createDiary!!, updateSelectedFriendView)
        }

    }

    private val updateSelectedFriendView : () -> Unit = {
        val friendArrays = arrayListOf(
            Pair(binding.createDiaryMe, binding.createDiaryMyName),
            Pair(binding.createDiarySecondFriend, binding.createDiarySecondFriendName),
            Pair(binding.createDiaryThirdFriend, binding.createDiaryThirdFriendName),
            Pair(binding.createDiaryFourthFriend, binding.createDiaryFourthFriendName)
        )

        createDiary!!.getSelectedFriend().forEachIndexed handler@{ index,  friendDto ->
            if(friendDto == null){
                friendArrays[index].first.root.visibility = View.INVISIBLE
                friendArrays[index].second.visibility = View.INVISIBLE
                return@handler
            }

            val (_, nickname, body,bodyColor, blushColor, item ) = friendDto

            friendArrays[index].first.root.visibility = View.VISIBLE
            friendArrays[index].second.visibility = View.VISIBLE
            friendArrays[index].second.text = nickname

            CharacterViewer(
                this,
                friendArrays[index].first,
                Character(
                    body,
                    bodyColor,
                    blushColor,
                    item
                )
            ).show()

            return@handler
        }
    }

    private fun update(){
        friendApiService.getFriends(
            success = getFriendHandler,
            fail = null
        )
        userApiService.getMe(
            success = getMeHandler,
            fail = null
        )
    }

    private fun createAddFriendDialog(friendId: Int, createDiary: CreateDiary, callback: () -> Unit){
        if(createDiary != null) {
            var dialog = CreateDiaryAddFriendPopupActivity(this, createDiary, friendId, callback)
            dialog.show()
            dialog.show()
            dialog.window?.setLayout(800, 1200)
        }
    }

    private val createDiaryHandler : (CreateDiaryResponseDto?) -> Unit = createDiaryHandler@{ response ->
        try {
            if(!response!!.status!!){
                throw Throwable()
            }
            viewHandler.goMainActivity()
        } catch (e: Throwable) {
            e.printStackTrace()
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

    private val getFriendHandler: (GetMyFriendsResponseDto?) -> Unit = handler@{ dto ->
        try {
            if(!dto?.status!!){
                throw Throwable()
            }
            createDiary!!.setFriends(dto.friends!!)
            updateSelectedFriendView()
        } catch (e: Throwable) {
            e.printStackTrace()
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

    private val getMeHandler: (GetMeResponseDto?) -> Unit = handler@{ dto ->
        try {
            createDiary!!.setMe(userDto = dto?.user!!)
            updateSelectedFriendView()
        } catch (e: Throwable) {
            e.printStackTrace()
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }

}