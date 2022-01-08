package com.example.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.api.user.dto.GetMeResponseDto
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.UserCharacterBinding
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.api.entity.Diary
import com.example.myapplication.api.user.UserApiProvider
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.DiaryCoverAdapter
import com.example.myapplication.api.auth.DiaryApiService
import com.example.myapplication.api.diary.DiaryApiProvider
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var diaryCoverAdapter: DiaryCoverAdapter
    var diaryList = mutableListOf<Diary>()

    private var tokenManager: TokenManager? = null;
    private var userApiProvider: UserApiProvider? = null;
    private var diaryApiProvider: DiaryApiProvider? = null;
    private var icon: UserCharacterBinding?= null;

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(applicationContext);
        userApiProvider = UserApiService(tokenManager!!).getProvider();
        diaryApiProvider = DiaryApiService(tokenManager!!).getProvider();
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        icon = binding.userCharacterIcon

        getUser()
        getMyDiaries()
        bindLayouts()
    }

    fun bindLayouts(){
        binding.diaryAddBtn.setOnClickListener {
            val intent = Intent(this, CharacterInitActivity::class.java)
            startActivity(intent)
        }
        diaryCoverAdapter = DiaryCoverAdapter(this)
        diaryCoverAdapter.diaryList = diaryList
        binding.diaryList.setLayoutManager(GridLayoutManager(this, 2))
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

    private fun getUser(){
        userApiProvider!!.getMe().enqueue(object : Callback<GetMeResponseDto> {
            override fun onResponse(
                call: Call<GetMeResponseDto>,
                response: Response<GetMeResponseDto>
            ) {
                Log.d("DEBUG", "GET_USER 성공");
                Log.d("DEBUG", response.body().toString())
                Log.d("DEBUG", response.toString())
                Log.d("DEBUG", response.headers().toString())


                getUserHandler(response.body())
            }

            override fun onFailure(call: Call<GetMeResponseDto>, t: Throwable) {
                Log.d("DEBUG", "GET_USER 실패")
            }
        })
    }

    fun getUserHandler(response: GetMeResponseDto?){
        val viewHandler = ViewHandler(this)

        if(
                viewHandler.goLoginActivityIfNull(response) ||
                viewHandler.goLoginActivityIfNull(response?.status) ||
                viewHandler.goLoginActivityIfNull(response?.user)
        ){
            return;
        }

        val dto = response!!

        if(dto.status == false){
            viewHandler.goLoginActivityAndRemoveTokens()
            return;
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

    private fun getMyDiaries(){
        diaryApiProvider!!.getDiaries().enqueue(object: Callback<GetMyDiariesResponseDto> {
            override fun onResponse(
                call: Call<GetMyDiariesResponseDto>,
                response: Response<GetMyDiariesResponseDto>
            ) {
                Log.d("DEBUG", "GET MY DIARIES 성공")
                Log.d("DEBUG", response.body().toString())
                Log.d("DEBUG", response.toString());

                getMyDiariesHandler(response.body());
            }

            override fun onFailure(call: Call<GetMyDiariesResponseDto>, t: Throwable) {
                Log.d("DEBUG", "GET MY DIARIES 실패")
            }
        })
    }

    fun getMyDiariesHandler(dto: GetMyDiariesResponseDto?){
        val viewHandler = ViewHandler(this);
        if(
            viewHandler.goLoginActivityIfNull(dto) ||
            viewHandler.goLoginActivityIfNull(dto?.diaries) ||
            viewHandler.goLoginActivityIfNull(dto?.status)
        ) {
            return;
        }



    }
}
