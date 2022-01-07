package com.example.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.UserCharacterBinding
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.dto.DiaryDto
import com.example.myapplication.api.user.UserApiProvider
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.api.dto.GetMeResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var diaryCoverAdapter: DiaryCoverAdapter
    var diaryList = mutableListOf<DiaryDto>()

    private var tokenManager: TokenManager? = null;
    private var userApiProvider: UserApiProvider? = null;
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(applicationContext);
        userApiProvider = UserApiService(tokenManager!!).getProvider();
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var icon = binding.userCharacterIcon

        settingUserIcon(icon)

        diaryCoverAdapter = DiaryCoverAdapter(this)
        binding.diaryList.adapter=diaryCoverAdapter

        diaryList.add(
            DiaryDto(
                "12345"
                ,"누구보다 빠르게 남들과는 다르게 색다르게 리듬을 타는 비트 위의 나그네"
                , LocalDate.now()
                , GetMeResponseDto(12345, "예그리나", 2, 1, 1, 1, 2)
                , null
            )
        )
        for (i in 1..5) {
            diaryList.add(
                DiaryDto(
                    "12345"
                    ,"다부숴!${i}"
                    , LocalDate.now()
                    , GetMeResponseDto(12345, "예그리나", 2, 1, 1, 1, 2)
                    , null
                )
            )
        }
        diaryList.add(
            DiaryDto(
                "12345"
                ,"내가그린기린그림은잘그린기린그림"
                , LocalDate.now()
                , GetMeResponseDto(12345, "예그리나", 2, 1, 1, 1, 2)
                , null
            )
        )

        diaryCoverAdapter.diaryList = diaryList
        binding.diaryList.setLayoutManager(GridLayoutManager(this, 2))

        // 수정해야 할 것 : api를 통해 유저의 정보를 받아올텐데 그 안에 있는 user nickname 변수로 text를 수정해주어야 한다.
        binding.userNickname.text = "예그리나"

        userApiProvider!!.getMe().enqueue(object : Callback<GetMeResponseDto> {
            override fun onResponse(
                call: Call<GetMeResponseDto>,
                response: Response<GetMeResponseDto>
            ) {
                println(response.body());
            }

            override fun onFailure(call: Call<GetMeResponseDto>, t: Throwable) {
                println(t);
            }
        })

        bindLayouts();
    }

    fun bindLayouts(){
        binding.diaryAddBtn.setOnClickListener {
            val intent = Intent(this, CharacterInitActivity::class.java)
            startActivity(intent)
        }
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

    fun getBodyColor(color: Int): Int {
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

    fun getBlush(blush: Int, shape: Int): Pair<Int, Int> {
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

    fun item_kind(item: Int): Int {
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

    fun settingUserIcon(icon: UserCharacterBinding) {
        var body_shape = CharacterInitActivity.character_init_body_shape
        var body_color = CharacterInitActivity.character_init_body_color
        var blush = CharacterInitActivity.character_init_blush
        var item = CharacterInitActivity.character_init_item

        icon.body.setImageResource(getShape(body_shape))
        icon.body.setColorFilter(resources.getColor(getBodyColor(body_color)))
        icon.blush.setColorFilter(resources.getColor(getBlush(blush, body_shape).first))
        icon.blush.setImageResource(getBlush(blush, body_shape).second)
        if (item == 1) {
            icon.item.visibility= View.INVISIBLE
        }
        else {
            icon.item.visibility=View.VISIBLE
            icon.item.setImageResource(item_kind(item))
        }
        icon.face.setImageResource(getFace(body_shape))
    }
}
