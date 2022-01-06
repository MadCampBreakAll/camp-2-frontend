package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.ActivityCharacterInitBinding
import com.example.myapplication.databinding.FragmentCharacterBodySelectBinding

class CharacterBodySelectFragment : Fragment() {
    private var _binding:FragmentCharacterBodySelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterBodySelectBinding.inflate(inflater, container, false)
        var character_init_binding=CharacterInitActivity.character_init_binding

        binding.bodyColorOneBtn.setOnClickListener{
            character_init_binding.userCharacterInit.body.setColorFilter(resources.getColor(R.color.dark_brown))
        }
        return binding.root
    }


}