package com.example.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCharacterInitBinding
import com.example.myapplication.databinding.FragmentCharacterBodyShapeSelectBinding
import java.util.*


class CharacterInitActivity : AppCompatActivity() {
    private var _binding: ActivityCharacterInitBinding? = null
    private val binding get() = _binding!!
    private val bodyshapeselectfragment by lazy {CharacterBodyShapeSelectFragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCharacterInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        character_init_binding = binding
        var next = binding.userCharacterInitNextBtn
        next.setColorFilter(resources.getColor(R.color.body_pink))

        supportFragmentManager.beginTransaction()
            .replace(R.id.selecting_fragment, bodyshapeselectfragment)
            .commit()

    }
    companion object {
        lateinit var character_init_binding : ActivityCharacterInitBinding
    }
}