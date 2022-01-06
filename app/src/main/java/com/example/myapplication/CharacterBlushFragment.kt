package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentCharacterBlushShapeBinding

class CharacterBlushFragment : Fragment() {
    private var _binding: FragmentCharacterBlushShapeBinding? = null
    private val binding get() = _binding!!
    private val bodyselectfragment by lazy {CharacterBodySelectFragment()}
    private val itemselectfragment by lazy {CharacterItemSelectFragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterBlushShapeBinding.inflate(inflater, container, false)

        var character_init_binding = CharacterInitActivity.character_init_binding
        character_init_binding.userCharacterInitType.text="Blush"

        var prev = character_init_binding.userCharacterInitPrevBtn
        prev.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selecting_fragment, bodyselectfragment)
                .commit()
        }

        var next = character_init_binding.userCharacterInitNextBtn
        next.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selecting_fragment, itemselectfragment)
                .commit()
        }

        setBlush(1)
        setBlush(2)
        setBlush(3)

        return binding.root
    }

    fun setBlush(button: Int) {
        var character_body_binding = CharacterInitActivity.character_init_binding

        when(button){
            1 -> {
                CharacterInitActivity.character_init_blush = 1
                binding.bodyColorOneBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.blush.setColorFilter(resources.getColor(R.color.blush_pink))
                    clickButton(button)
                }

            }
            2 -> {
                CharacterInitActivity.character_init_blush = 2
                binding.bodyColorTwoBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.blush.setColorFilter(resources.getColor(R.color.blush_orange))
                    clickButton(button)
                }
            }
            3 -> {
                CharacterInitActivity.character_init_blush = 3
                binding.bodyColorThreeBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.blush.setColorFilter(
                        resources.getColor(
                            R.color.blush_blue
                        )
                    )
                    clickButton(button)
                }
            }
        }

    }

    fun clickButton(button: Int) {
        var i = 0
        when(button) {
            1 -> {
                binding.bodyColorOneBtn.setImageResource(R.drawable.one_btn_click)
                binding.bodyColorTwoBtn.setImageResource(R.drawable.two_btn_nonclick)
                binding.bodyColorThreeBtn.setImageResource(R.drawable.three_btn_nonclick)
            }
            2 -> {
                binding.bodyColorTwoBtn.setImageResource(R.drawable.two_btn_click)
                binding.bodyColorOneBtn.setImageResource(R.drawable.one_btn_nonclick)
                binding.bodyColorThreeBtn.setImageResource(R.drawable.three_btn_nonclick)
            }
            3 -> {
                binding.bodyColorThreeBtn.setImageResource(R.drawable.three_btn_click)
                binding.bodyColorTwoBtn.setImageResource(R.drawable.two_btn_nonclick)
                binding.bodyColorOneBtn.setImageResource(R.drawable.one_btn_nonclick)
            }
        }
    }

}