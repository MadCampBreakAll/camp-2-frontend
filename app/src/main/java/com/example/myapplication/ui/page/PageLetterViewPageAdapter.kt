package com.example.myapplication.ui.page

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.diary.dto.NextUserDto
import com.example.myapplication.api.page.dto.PageDto
import com.example.myapplication.api.page.dto.UserDto
import com.example.myapplication.databinding.PageLetterItemBinding
import com.example.myapplication.databinding.UserCharacterBinding
import com.example.myapplication.ui.join.CharacterInitActivity
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

class PageLetterViewPageAdapter(private val context: Context): RecyclerView.Adapter<PageLetterViewPageAdapter.ViewHolder>() {
    private var pageList = mutableListOf<PageDto>()

    fun addAllPage(pages: List<PageDto>){
        this.pageList.addAll(pages);
    }

    fun clear(){
        this.pageList.clear()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PageLetterItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return(ViewHolder(binding))
    }

    override fun getItemCount(): Int = pageList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pageList[position])
    }

    inner class ViewHolder(binding: PageLetterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var writenDate = binding.innerPageWrittenDate
        private var background = binding.pageBackgroundColor

        private var monoon = binding.monoonBackground
        private var dailyColor = binding.innerPageDailyColor
        private var nextUser = binding.innerPageNextUserCharacter
        private var body = binding.innerPageText
        private var writer = binding.innerPageWriteUserCharacter
        private var title = binding.pageTitle

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(page: PageDto) {
            try {

                val (_, _title, _body, color, img, user, createdAt, _nextUser) = page

                writenDate.text = createdAt!!.time.toString()
                dailyColor.setColorFilter(Color.parseColor(color))
                body.text = _body
                title.text = _title

                // background.setBackgroundColor(Color.parseColor(color))

                bindWriter(user!!)
                bindNextUser(_nextUser!!)
            } catch (e: Throwable) {
                e.printStackTrace()
                Log.d("DEBUG", "PAGE LETTER VIEW PAGE ADAPTER BIND")
            }
        }

        private fun bindNextUser(_nextUser: NextUserDto){
            try {
                val (_, _, body, bodyColor, blushColor, item) = _nextUser
                CharacterViewer(
                    context,
                    nextUser,
                    Character(
                        body!!,
                        bodyColor!!,
                        blushColor!!,
                        item!!
                    )
                ).show()
            } catch (e: Throwable) {
                e.printStackTrace()
                Log.d("DEBUG", "BIND NEXT USER FAIL")
            }
        }

        private fun bindWriter(_writer: UserDto){
            try {
                val (_, _, body, bodyColor, blushColor, item) = _writer
                val writerData = Character(body!!, bodyColor!!, blushColor!!, item!!)
                CharacterViewer(
                    context,
                    writer,
                    writerData
                ).show()
            } catch (e: Throwable) {
                Log.d("DEBUG", "BIND WRITER FAIL")
            }
        }

    }

}