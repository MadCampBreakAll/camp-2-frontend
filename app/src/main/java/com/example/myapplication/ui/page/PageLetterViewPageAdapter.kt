package com.example.myapplication.ui.page

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication.api.diary.dto.NextUserDto
import com.example.myapplication.api.page.dto.PageDto
import com.example.myapplication.api.page.dto.UserDto
import com.example.myapplication.databinding.PageLetterImageItemBinding
import com.example.myapplication.databinding.PageLetterItemBinding
import com.example.myapplication.ui.main.Setting
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.SimpleDate
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.AsyncTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.MalformedURLException


class PageLetterViewPageAdapter(
    private val context: Context
) : RecyclerView.Adapter<PageLetterViewPageAdapter.PageLetterHolder>() {
    private var pageList = mutableListOf<PageDto>()

    fun addAllPage(pages: List<PageDto>) {
        this.pageList.addAll(pages);
    }

    fun clear() {
        this.pageList.clear()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageLetterHolder {
        if(viewType == 1){
            return PageLetterImageItemHolder(
                PageLetterImageItemBinding.inflate(LayoutInflater.from(context), parent, false)
            )
        }
        val binding = PageLetterItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return PageLetterItemHolder(binding)
    }

    override fun getItemCount(): Int = pageList.size

    override fun getItemViewType(position: Int): Int {
        if(!pageList[position].img.isNullOrBlank()) {
            return 1
        }
        return 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PageLetterHolder, position: Int) {
        holder.bind(pageList[position])
    }

    abstract class PageLetterHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(page: PageDto)
    }

    inner class PageLetterImageItemHolder(private val binding: PageLetterImageItemBinding): PageLetterHolder(binding){
        private var writenDate = binding.innerPageWrittenDate
        private var background = binding.pageBackgroundColor

        private var monoon = binding.monoonBackground
        private var dailyColor = binding.innerPageDailyColor
        private var nextUser = binding.innerPageNextUserCharacter
        private var body = binding.innerPageText
        private var writer = binding.innerPageWriteUserCharacter
        private var title = binding.pageTitle
        private var writerNickname = binding.writerNickname
        private var nextUserNickname = binding.nextUserNickname

        @RequiresApi(Build.VERSION_CODES.N)
        @SuppressLint("SimpleDateFormat")
        override fun bind(page: PageDto) {
            try {
                val (_, _title, _body, color, img, user, createdAt, _nextUser) = page
                writenDate.text = SimpleDate.getUTCTime(createdAt!!)
                dailyColor.setColorFilter(Color.parseColor(color))
                body.text = _body!!
                title.text = _title!!

                writerNickname.text = user!!.nickname
                nextUserNickname.text = _nextUser!!.nickname

                background.setBackgroundColor(
                    Color.parseColor(
                        Setting.backgroundColor
                    )
                )
                if (Setting.page == 0) {
                    monoon.visibility = View.INVISIBLE
                } else {
                    monoon.visibility = View.VISIBLE
                }

                CoroutineScope(Dispatchers.Main).launch {
                    val bitmap = withContext(Dispatchers.IO){
                        ImageLoader.loadImage(img!!)
                    }
                    println(bitmap)
                    binding.picture.setImageBitmap(bitmap)
                }


                bindWriter(user!!)
                bindNextUser(_nextUser!!)
            } catch (e: Throwable) {
                e.printStackTrace()
                Log.d("DEBUG", "PAGE LETTER VIEW PAGE ADAPTER BIND")
            }
        }

        private fun bindNextUser(_nextUser: NextUserDto) {
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

        private fun bindWriter(_writer: UserDto) {
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

        fun getBitmapFromURL(src: String?): Bitmap? {

            val SDK_INT = Build.VERSION.SDK_INT
            if (SDK_INT > 8) {
                val policy = ThreadPolicy.Builder()
                    .permitAll().build()
                StrictMode.setThreadPolicy(policy)
                return try {
                    val url = URL(src)
                    val connection =
                        url.openConnection() as HttpURLConnection
                    connection.doInput = true
                    connection.connect()
                    val input = connection.inputStream
                    BitmapFactory.decodeStream(input)
                } catch (e: IOException) {
                    // Log exception
                    null
                }
            }

            return null
        }
    }

    inner class PageLetterItemHolder(binding: PageLetterItemBinding): PageLetterHolder(binding){
        private var writenDate = binding.innerPageWrittenDate
        private var background = binding.pageBackgroundColor

        private var monoon = binding.monoonBackground
        private var dailyColor = binding.innerPageDailyColor
        private var nextUser = binding.innerPageNextUserCharacter
        private var body = binding.innerPageText
        private var writer = binding.innerPageWriteUserCharacter
        private var title = binding.pageTitle
        private var writerNickname = binding.writerNickname
        private var nextUserNickname = binding.nextUserNickname

        @RequiresApi(Build.VERSION_CODES.N)
        @SuppressLint("SimpleDateFormat")
        override fun bind(page: PageDto) {
            try {
                val (_, _title, _body, color, img, user, createdAt, _nextUser) = page
                writenDate.text = SimpleDate.getUTCTime(createdAt!!)
                dailyColor.setColorFilter(Color.parseColor(color))
                body.text = _body
                title.text = _title

                writerNickname.text = user!!.nickname
                nextUserNickname.text = _nextUser!!.nickname

                background.setBackgroundColor(
                    Color.parseColor(
                        Setting.backgroundColor
                    )
                )
                if (Setting.page == 0) {
                    monoon.visibility = View.INVISIBLE
                } else {
                    monoon.visibility = View.VISIBLE
                }
                bindWriter(user!!)
                bindNextUser(_nextUser!!)
            } catch (e: Throwable) {
                e.printStackTrace()
                Log.d("DEBUG", "PAGE LETTER VIEW PAGE ADAPTER BIND")
            }
        }

        private fun bindNextUser(_nextUser: NextUserDto) {
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

        private fun bindWriter(_writer: UserDto) {
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

object ImageLoader{
    suspend fun loadImage(imageUrl: String): Bitmap? {
        val bmp: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val stream = url.openStream()
            return BitmapFactory.decodeStream(stream)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmp
    }
}