package com.example.myapplication.ui.diary

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.dto.PageDto
import com.example.myapplication.databinding.PageLetterItemBinding
import com.example.myapplication.databinding.UserCharacterBinding
import com.example.myapplication.ui.join.CharacterInitActivity
import java.time.LocalDate

// 이 어뎁터는 diary의 letter pages를 담는 fragment_page_letter의 viewpager이 각각의 page들에 값을 binding하여 view를 만들어준다.
// page_letter에 적절한 값을 바인딩할 수 있어야 한다.
// DiaryInnerActivity 안에 있는 viewpager에 page view를 바인딩
// viewpager은 diaryinneractivity에 있다.

class PageLetterViewPageAdapter(private val context: Context): RecyclerView.Adapter<PageLetterViewPageAdapter.ViewHolder>() {
    private lateinit var innerpagerBinding: PageLetterItemBinding
    var pageList = mutableListOf<PageDto>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        innerpagerBinding = PageLetterItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return(ViewHolder(innerpagerBinding))
    }

    override fun getItemCount(): Int = pageList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pageList[position])
    }

    inner class ViewHolder(binding: PageLetterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var writenDate = binding.innerPageWrittenDate
        private var background = binding.pageBackgroundLayout
        private var dailyColor = binding.innerPageDailyColor
        private var nextUser = binding.innerPageNextUserCharacter
        private var body = binding.innerPageText
        private var writer = binding.innerPageWriteUserCharacter
        private var title = binding.pageTitle

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(page: PageDto) {
            writenDate.text = LocalDate.now().toString()

            // background는 설정을 변경하는 화면의 activity 혹은 fragment에서 저장된 companion object 값에 따라서 setting 하는 것으로 하자.

            dailyColor.setColorFilter(Color.parseColor(page.color))

//            settingOthersIcon(nextUser, user의 정보들을 뒤의 인자로 추가)
//            settingOthersIcon(writer, writer의 정보를 뒤의 인자로 추가)
//            background.setBackgroundColor(Color.parseColor(page.배경 색상 정보가 있어야 할 듯 하다))
            body.text = page.body
            title.text = page.title

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

            settingOthersIcon(icon, body_shape, body_color, blush, item)
        }

        fun settingOthersIcon(icon: UserCharacterBinding, body_shape: Int, body_color: Int, blush: Int, item: Int){
            icon.body.setImageResource(getShape(body_shape))
            icon.body.setColorFilter(Color.parseColor(context.getString(getBodyColor(body_color))))
            icon.blush.setColorFilter(Color.parseColor(context.getString(getBlush(blush, body_shape).first)))
            icon.blush.setImageResource(getBlush(blush, body_shape).second)
            if (item == 1) {
                icon.item.visibility= View.INVISIBLE
            }
            else {
                icon.item.visibility= View.VISIBLE
                icon.item.setImageResource(item_kind(item))
            }
            icon.face.setImageResource(getFace(body_shape))
        }
    }

}