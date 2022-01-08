package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.api.entity.Diary
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.*

class DiaryCoverAdapter(private val context: Context): RecyclerView.Adapter<DiaryCoverAdapter.ViewHolder>() {
    private lateinit var diaryBinding : DiaryBinding

    var diaryList = mutableListOf<Diary>()

    fun addDiary(diary: Diary){
        diaryList.add(diary);
    }

    fun clearDiary(){
        diaryList.clear();
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryCoverAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.diary, parent, false)

        diaryBinding = DiaryBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(diaryBinding)
    }

    override fun getItemCount(): Int = diaryList.size

    override fun onBindViewHolder(holder: DiaryCoverAdapter.ViewHolder, position: Int) {

        holder.title.setSelected(true)
        holder.title.ellipsize= TextUtils.TruncateAt.MARQUEE
        holder.title.marqueeRepeatLimit = -1

        // recycler view의 item 열 사이의 간격 조정
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height=600
        holder.itemView.requestLayout()

        holder.bind(diaryList[position])

        diaryBinding.diaryImage.setOnClickListener {
            val intent = Intent(context, DiaryInnerActivity::class.java)
            intent.putExtra("diaryId", diaryList[position].id)
            startActivity(context, intent, null)
        }
    }

    inner class ViewHolder(binding: DiaryBinding) : RecyclerView.ViewHolder(binding.root){
        var icon = binding.diaryCoverNextWriterIcon

        var title: TextView = binding.diaryTitle

        fun bind(item: Diary) {
            // 이 부분에 다음 작성자가 나인 diary의 경우에는 alarm imageView가 visible하도록 설정
            title.text = item.title

            // 수정해야 할 점 : api를 통해 diary 안에 있는 사람 정보 작성 순서대로 정렬된 list를 받을텐데
            // 그 list에 담긴 정보를 통해 diaryCovoerFirstOrder~Four까지 icon binding을 받아서 설정해줘야 한다.
            // 이 때는 settingUserIcon이 아니라 settingOthersIcon 함수를 통해 아이콘 정보를 인자로 넘겨주어야 한다.
            settingUserIcon(icon)
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
                icon.item.visibility=View.VISIBLE
                icon.item.setImageResource(item_kind(item))
            }
            icon.face.setImageResource(getFace(body_shape))
        }
    }

}