package com.example.myapplication.ui.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.api.user.dto.GetMeResponseDto
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.UserCharacterBinding
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.ui.join.CharacterInitActivity
import com.example.myapplication.R
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.dto.DiaryDto
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto
import com.example.myapplication.util.TokenManager
import com.example.myapplication.util.ViewHandler

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var diaryCoverAdapter: DiaryCoverAdapter
    private lateinit var userApiService: UserApiService
    private lateinit var diaryApiService: DiaryApiService
    private lateinit var viewHandler: ViewHandler
    private lateinit var icon: UserCharacterBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        update()
    }

    private fun init(){
        val tokenManager = TokenManager(applicationContext)
        userApiService = UserApiService(tokenManager)
        diaryApiService = DiaryApiService(tokenManager)
        binding = ActivityMainBinding.inflate(layoutInflater)
        icon = binding.userCharacterIcon
        viewHandler = ViewHandler(this);
        diaryCoverAdapter = DiaryCoverAdapter(this)
        diaryCoverAdapter.diaryList = mutableListOf<DiaryDto>();
        binding.diaryList.adapter = diaryCoverAdapter;
        binding.diaryList.setLayoutManager(GridLayoutManager(this, 2))
    }

    private fun bind() {
        setContentView(binding.root)
        binding.diaryAddBtn.setOnClickListener {
            viewHandler.goCreateDiaryActivity();
        }
        binding.goFriendActivity.setOnClickListener {
            viewHandler.goFriendActivity();
        }
    }

    private fun update(){
        userApiService.getMe(
            success = getUserHandler,
            fail = null
        )
        diaryApiService.getDiaries(
            success = getMyDiariesHandler,
            fail = null
        )
    }

    fun getShape(shape: Int): Int {
        var shape_draw = 0
        when(shape){
            1 -> shape_draw = R.drawable.body_shape_triangle
            2 -> shape_draw = R.drawable.body_shape_cloud
            3 -> shape_draw = R.drawable.body_shape_bean
            4 -> shape_draw = R.drawable.body_shape_square
            5 -> shape_draw = R.drawable.body_shape_bread
        }
        return shape_draw
    }

    private fun getBodyColor(color: Int): Int {
        var color_draw = 0
        when(color){
            1 -> color_draw = R.color.body_blue
            2 -> color_draw = R.color.body_dark_navy
            3 -> color_draw = R.color.body_brown
            4 -> color_draw = R.color.body_red
            5 -> color_draw = R.color.body_yellow
        }
        return color_draw
    }

    private fun getBlush(blush: Int, shape: Int): Pair<Int, Int> {
        var blush_draw = 0
        var blush_pos_draw = 0
        when(shape){
            1 -> {
                when(blush){
                    1 -> {
                        blush_draw = R.color.blush_pink
                        blush_pos_draw = R.drawable.blush_triangle
                    }
                    2 -> {
                        blush_draw = R.color.blush_orange
                        blush_pos_draw = R.drawable.blush_triangle
                    }
                    3 -> {
                        blush_draw = R.color.blush_blue
                        blush_pos_draw = R.drawable.blush_triangle
                    }
                }
            }
            2 -> {
                when(blush){
                    1 -> {
                        blush_draw = R.color.blush_pink
                        blush_pos_draw = R.drawable.blush_cloud
                    }
                    2 -> {
                        blush_draw = R.color.blush_orange
                        blush_pos_draw = R.drawable.blush_cloud
                    }
                    3 -> {
                        blush_draw = R.color.blush_blue
                        blush_pos_draw = R.drawable.blush_cloud
                    }
                }
            }
            3 -> {
                when(blush){
                    1 -> {
                        blush_draw = R.color.blush_pink
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                    2 -> {
                        blush_draw = R.color.blush_orange
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                    3 -> {
                        blush_draw = R.color.blush_blue
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                }
            }
            4 -> {
                when(blush){
                    1 -> {
                        blush_draw = R.color.blush_pink
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                    2 -> {
                        blush_draw = R.color.blush_orange
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                    3 -> {
                        blush_draw = R.color.blush_blue
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                }
            }
            5 -> {
                when(blush){
                    1 -> {
                        blush_draw = R.color.blush_pink
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                    2 -> {
                        blush_draw = R.color.blush_orange
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                    3 -> {
                        blush_draw = R.color.blush_blue
                        blush_pos_draw = R.drawable.blush_bean_bread_square
                    }
                }
            }
        }
        return Pair(blush_draw, blush_pos_draw)
    }

    private fun item_kind(item: Int): Int {
        var shape = CharacterInitActivity.character_init_body_shape
        var result_item = 0
        when (shape) {
            1 -> {
                when (item) {
                    2 -> result_item = R.drawable.item_ribbon_triangle
                    3 -> result_item = R.drawable.item_crown_triangle
                    4 -> result_item = R.drawable.item_merong_triangle
                    5 -> result_item = R.drawable.item_glasses_triangle
                }
            }
            2 -> {
                when (item) {
                    2 -> result_item = R.drawable.item_ribbon_cloud
                    3 -> result_item = R.drawable.item_crown_cloud
                    4 -> result_item = R.drawable.item_merong_cloud
                    5 -> result_item = R.drawable.item_glasses_cloud
                }
            }
            else -> {
                when (item) {
                    2 -> result_item = R.drawable.item_ribbon_bean_bread_square
                    3 -> result_item = R.drawable.item_crown_bean_bread_square
                    4 -> result_item = R.drawable.item_merong_bean_bread_square
                    5 -> result_item = R.drawable.item_glasses_bean_bread_square
                }
            }
        }
        return result_item
    }

    fun getFace(shape: Int): Int {
        var face_draw = 0
        when(shape) {
            1 -> {
                face_draw = R.drawable.face_traingle
            }
            2 -> {
                face_draw = R.drawable.face_cloud
            }
            else -> {
                face_draw = R.drawable.face_bean_bread_square
            }
        }
        return face_draw
    }

    private val getUserHandler : (GetMeResponseDto?) -> Unit = handler@{ response ->
        if(
                viewHandler.goLoginActivityIfNull(response) ||
                viewHandler.goLoginActivityIfNull(response?.status) ||
                viewHandler.goLoginActivityIfNull(response?.user)
        ){
            return@handler;
        }

        val dto = response!!

        if(dto.status == false){
            viewHandler.goLoginActivityAndRemoveTokens()
            return@handler;
        }

        var body_shape = CharacterInitActivity.character_init_body_shape
        var body_color = CharacterInitActivity.character_init_body_color
        var blush = CharacterInitActivity.character_init_blush
        var item = CharacterInitActivity.character_init_item

        var (_, user) = dto

        icon!!.body.setImageResource(getShape(user?.body?:1))
        icon!!.body.setColorFilter(resources.getColor(getBodyColor(user?.bodyColor?:1)))
        icon!!.blush.setColorFilter(resources.getColor(getBlush(1, body_shape).first))
        icon!!.blush.setImageResource(getBlush(blush, body_shape).second)
        if (item == 1) {
            icon!!.item.visibility= View.INVISIBLE
        }
        else {
            icon!!.item.visibility=View.VISIBLE
            icon!!.item.setImageResource(item_kind(user?.item?:1))
        }
        icon!!.face.setImageResource(getFace(user?.body?:1))
        setUserNickname(nickname = user?.nickname?:"unknown")
    }

    private fun setUserNickname(nickname: String){
        this.binding.userNickname.text = nickname
    }

     private val getMyDiariesHandler: (GetMyDiariesResponseDto?) -> Unit = handler@{ response ->
        try {
            if(!response?.status!!){
                throw Error()
            }

            diaryCoverAdapter.run {
                clearDiary()
                addAllDiary(response.diaries!!)
                notifyDataSetChanged()
            };

        } catch (e: Throwable) {
            viewHandler.goLoginActivityAndRemoveTokens()
        }
    }
}
