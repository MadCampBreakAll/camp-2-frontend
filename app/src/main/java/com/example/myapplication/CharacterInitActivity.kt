package com.example.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCharacterInitBinding
import java.util.*


class CharacterInitActivity : AppCompatActivity() {
    private var _binding: ActivityCharacterInitBinding? = null
    private val binding get() = _binding!!
    private val bodyselectfragment by lazy {CharacterBodySelectFragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCharacterInitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.userCharacterInit.body.setColorFilter(resources.getColor(R.color.dark_brown))
//        binding.userCharacterInit.body.setImageResource(R.drawable.five_btn_nonclick)
        supportFragmentManager.beginTransaction()
            .replace(R.id.selecting_fragment, bodyselectfragment)
            .commit()

    }
}