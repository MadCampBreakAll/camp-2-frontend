package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.diary.dto.ChamyeoUserDto
import com.example.myapplication.api.diary.dto.DiaryDto
import com.example.myapplication.api.diary.dto.NextUserDto
import com.example.myapplication.databinding.*
import com.example.myapplication.ui.page.DiaryInnerActivity
import com.example.myapplication.ui.join.CharacterInitActivity
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.ViewHandler
import com.google.gson.annotations.SerializedName
import org.w3c.dom.Text
import java.util.*

class DiaryCoverAdapter(private val context: Context): RecyclerView.Adapter<DiaryCoverAdapter.ViewHolder>() {
    var diaryList = mutableListOf<DiaryDto>()

    fun addDiary(diary: DiaryDto){
        diaryList.add(diary)
    }

    fun addAllDiary(diary: List<DiaryDto>) {
        diaryList.addAll(diary)
    }

    fun clearDiary(){
        diaryList.clear()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val diaryBinding = DiaryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(diaryBinding)
    }

    override fun getItemCount(): Int = diaryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height=600
        holder.itemView.requestLayout()
        holder.bind(diaryList[position])
    }

    inner class ViewHolder(private val binding: DiaryBinding) : RecyclerView.ViewHolder(binding.root){
        private var titlebinding: TextView = binding.diaryTitle

        @SuppressLint("StringFormatInvalid")
        fun bind(item: DiaryDto) {
            val (_, title, _, nextUser, chamyeoUsers) = item
            titlebinding.run {
                isSelected = true
                ellipsize = TextUtils.TruncateAt.MARQUEE
                marqueeRepeatLimit = -1
                text = title
            }
            binding.diaryImage.setOnClickListener {
                val intent = Intent(context, DiaryInnerActivity::class.java)
                intent.putExtra(context.resources.getString(R.string.diary_id), item.id)
                startActivity(context, intent, null)
            }
            bindNextUser(nextUser)
            bindChamyeonUsers(chamyeoUsers)
        }

        private fun bindNextUser(nextUser: NextUserDto){
            try {
                val (_, _, body, bodyColor, blushColor, item) = nextUser
                CharacterViewer(
                    context,
                    binding.diaryCoverNextWriterIcon,
                    Character(
                        body!!,
                        bodyColor!!,
                        blushColor!!,
                        item!!
                    )
                ).show()
            } catch (e: Throwable) {
                Log.d("DEBUG", "bindNextUser Error")
                Log.d("DEBUG", e.toString())
            }
        }

        private fun bindChamyeonUsers(chamyeoUserDto: List<ChamyeoUserDto>){
            try {
                val candidateBindings = arrayListOf<UserCharacterBinding>(
                    binding.diaryCoverFirstOrderIcon,
                    binding.diaryCoverSecondOrderIcon,
                    binding.diaryCoverThirdOrderIcon,
                    binding.diaryCoverFourthOrderIcon
                )
                chamyeoUserDto.forEachIndexed { index, chamyeoUserDto ->
                    val (_, _, body, bodyColor, blushColor, item) = chamyeoUserDto
                    CharacterViewer(
                        context,
                        candidateBindings[index],
                        Character(
                            body!!,
                            bodyColor!!,
                            blushColor!!,
                            item!!
                        )
                    ).show()
                }
            } catch (e: Throwable) {
                Log.d("DEBUG", "bindChamyeonUsers Error")
                Log.d("DEBUG", e.toString())
            }
        }
    }

}