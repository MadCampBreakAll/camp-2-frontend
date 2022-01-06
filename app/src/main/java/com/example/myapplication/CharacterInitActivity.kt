package com.example.myapplication


import android.os.Bundle
import android.view.View
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

        binding.userCharacterInit.item.visibility= View.INVISIBLE

        character_init_binding = binding
        var next = binding.userCharacterInitNextBtn
        next.setColorFilter(resources.getColor(R.color.body_pink))

        var prev = binding.userCharacterInitPrevBtn
        prev.setColorFilter(resources.getColor(R.color.body_pink))
        prev.scaleX=-1f

        supportFragmentManager.beginTransaction()
            .replace(R.id.selecting_fragment, bodyshapeselectfragment)
            .commit()
    }
    companion object {
        lateinit var character_init_binding : ActivityCharacterInitBinding
        var character_init_body_color = 1
        var character_init_body_shape = 1
        var character_init_blush = 1
        var character_init_item = 1
    }
}