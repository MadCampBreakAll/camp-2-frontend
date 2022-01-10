package com.example.myapplication.ui.setting.backfont

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.BackgroundColorItemBinding
import com.example.myapplication.ui.main.Setting
import com.example.myapplication.ui.page.PageLetterViewPageAdapter

class BackgroundColorAdapter(private val context: Context, private val callback: (String) -> Unit): RecyclerView.Adapter<BackgroundColorAdapter.ViewHolder>() {
    private val colors = mutableListOf(
        "#fff1e6", "#edede4",
        "#f1ebd6", "#f1ebd6",
        "#f1cabc", "#eecfd3",
        "#d6cbe8", "#dae5d0",
        "#d3d4b2", "#abbaaf",
        "#ced2da", "#deb878",
        "#838a7d", "#a08fa6",
        "#8484a2", "#676772",
        "#c9ada7", "#f2e9e4")
    private var check: ImageView? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BackgroundColorAdapter.ViewHolder {
        val binding = BackgroundColorItemBinding.inflate(LayoutInflater.from(context), parent, false)

        binding.checkedbox.setImageResource(R.drawable.checkbox)
        return (ViewHolder(binding))
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: BackgroundColorAdapter.ViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    inner class ViewHolder(binding: BackgroundColorItemBinding): RecyclerView.ViewHolder(binding.root){
        private val background = binding.backgroundColor
        private val checkbox = binding.checkedbox

        fun bind(item: String){
            background.setBackgroundColor(Color.parseColor(item))
            background.setOnClickListener {
                checkbox.setImageResource(R.drawable.checkedbox)
                check?.setImageResource(R.drawable.checkbox)
                check = checkbox
                callback(item)
            }
        }
    }
}