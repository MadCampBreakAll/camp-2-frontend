package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.api.user.dto.GetMeResponseDto
import com.example.myapplication.api.user.dto.GetMyFriendsResponseDto
import com.example.myapplication.api.user.dto.GetPendingFriendResponseDto
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.UserCharacterBinding
import com.example.myapplication.api.user.UserApiProvider
import com.example.myapplication.api.user.UserApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var tokenManager: TokenManager? = null;
    private var userApiProvider: UserApiProvider? = null;
    private var icon: UserCharacterBinding?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(applicationContext);
        userApiProvider = UserApiService(tokenManager!!).getProvider();
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        icon = binding.userCharacterIcon

        getUser();
        bindLayouts();

        userApiProvider!!.getFriends().enqueue(object: Callback<GetMyFriendsResponseDto>{
            override fun onResponse(
                call: Call<GetMyFriendsResponseDto>,
                response: Response<GetMyFriendsResponseDto>
            ) {
                Log.d("DEBUG", "getFriends 성공")
                Log.d("DEBUG", response.body().toString())
                println(response.body().toString())
            }

            override fun onFailure(call: Call<GetMyFriendsResponseDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        userApiProvider!!.getFendingFriends().enqueue(object: Callback<GetPendingFriendResponseDto>{
            override fun onResponse(
                call: Call<GetPendingFriendResponseDto>,
                response: Response<GetPendingFriendResponseDto>
            ) {
                Log.d("DEBUG", "getPendingFriends 성공")
                Log.d("DEBUG", response.body().toString())
            }

            override fun onFailure(call: Call<GetPendingFriendResponseDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        userApiProvider!!.

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
        val dto = response!!

        if(viewHandler.goLoginActivityIfNull(response)){
            return;
        }

        if(dto!!.status == false){
            viewHandler.goLoginActivityAndRemoveTokens()
            return;
        }

        var body_shape = CharacterInitActivity.character_init_body_shape
        var body_color = CharacterInitActivity.character_init_body_color
        var blush = CharacterInitActivity.character_init_blush
        var item = CharacterInitActivity.character_init_item

        var (_, user) = dto!!;

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

    fun setUserNickname(nickname: String){
        this.binding.userNickname.text = nickname
    }

}
