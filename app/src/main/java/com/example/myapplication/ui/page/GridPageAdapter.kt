package com.example.myapplication.ui.page

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.friend.dto.FriendDto
import com.example.myapplication.api.page.dto.PageDto
import com.example.myapplication.databinding.CreateDiaryAddingFriendItemBinding
import com.example.myapplication.databinding.GridViewItemBinding
import com.example.myapplication.ui.diary.create.CreateDiaryFriendListAdapter
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.SimpleDate

class GridPageAdapter(
    private val context: Context
) : RecyclerView.Adapter<GridPageAdapter.ViewHolder>() {
    private var pageList = mutableListOf<PageDto>()

    fun addPage(page: PageDto){
        pageList.add(page)
    }

    fun addPage(pages: List<PageDto>){
        pageList.addAll(pages)
    }

    fun clear(){
        pageList.clear()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridPageAdapter.ViewHolder {
        val pageBinding = GridViewItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(pageBinding)
    }

    override fun getItemCount(): Int = pageList.size

    override fun onBindViewHolder(holder: GridPageAdapter.ViewHolder, position: Int) {
        holder.bind(pageList[position])
    }

    inner class ViewHolder(private val binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(pageDto: PageDto){
            binding.date.text = SimpleDate.getUTCTime(pageDto.createdAt!!)
            binding.nickname.text = pageDto.user!!.nickname
            binding.title.text = pageDto.title
            val (_, _, body, bodyColor, blushColor, item) = pageDto.user
            CharacterViewer(
                context,
                binding.writer,
                Character(
                    body!!,
                    bodyColor!!,
                    blushColor!!,
                    item!!
                )
            ).show()
        }
    }
}
