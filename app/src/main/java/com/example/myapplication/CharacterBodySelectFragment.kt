package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.myapplication.databinding.ActivityCharacterInitBinding
import com.example.myapplication.databinding.FragmentCharacterBodySelectBinding

class CharacterBodySelectFragment : Fragment() {
    private var _binding:FragmentCharacterBodySelectBinding? = null
    private val binding get() = _binding!!
    private val bodyshapeselectfragment by lazy {CharacterBodyShapeSelectFragment()}
    private val bodyblushselectfragment by lazy {CharacterBlushFragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterBodySelectBinding.inflate(inflater, container, false)

        var character_init_binding = CharacterInitActivity.character_init_binding
        character_init_binding.userCharacterInitType.text="Body Color"

        var prev = character_init_binding.userCharacterInitPrevBtn
        prev.visibility=View.VISIBLE
        prev.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selecting_fragment, bodyshapeselectfragment)
                .commit()
        }

        var next = character_init_binding.userCharacterInitNextBtn
        next.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selecting_fragment, bodyblushselectfragment)
                .commit()
        }

        setColor(1)
        setColor(2)
        setColor(3)
        setColor(4)
        setColor(5)
        return binding.root
    }

    fun setColor(button: Int) {
        var character_body_binding = CharacterInitActivity.character_init_binding
            when(button){
                1 -> {
                    binding.bodyColorOneBtn.setOnClickListener {
                        CharacterInitActivity.character_init_body_color = 1
                        character_body_binding.userCharacterInit.body.setColorFilter(resources.getColor(R.color.body_blue))
                        clickButton(button)
                    }

                }
                2 -> {
                    binding.bodyColorTwoBtn.setOnClickListener {
                        CharacterInitActivity.character_init_body_color = 2
                        character_body_binding.userCharacterInit.body.setColorFilter(resources.getColor(R.color.body_purple))
                        clickButton(button)
                        }
                }
                3 -> {
                    binding.bodyColorThreeBtn.setOnClickListener {
                        CharacterInitActivity.character_init_body_color = 3
                        character_body_binding.userCharacterInit.body.setColorFilter(
                            resources.getColor(
                                R.color.body_brown
                            )
                        )
                        clickButton(button)
                    }
                }
                4 -> {
                    binding.bodyColorFourBtn.setOnClickListener {
                        CharacterInitActivity.character_init_body_color = 4
                        character_body_binding.userCharacterInit.body.setColorFilter(
                            resources.getColor(
                                R.color.body_pink
                            )
                        )
                        clickButton(button)
                    }
                }
                5 -> {
                    binding.bodyColorFiveBtn.setOnClickListener {
                        CharacterInitActivity.character_init_body_color = 5
                        character_body_binding.userCharacterInit.body.setColorFilter(
                            resources.getColor(
                                R.color.body_yellow
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
                binding.bodyColorFourBtn.setImageResource(R.drawable.four_btn_nonclick)
                binding.bodyColorFiveBtn.setImageResource(R.drawable.five_btn_nonclick)
            }
            2 -> {
                binding.bodyColorTwoBtn.setImageResource(R.drawable.two_btn_click)
                binding.bodyColorOneBtn.setImageResource(R.drawable.one_btn_nonclick)
                binding.bodyColorThreeBtn.setImageResource(R.drawable.three_btn_nonclick)
                binding.bodyColorFourBtn.setImageResource(R.drawable.four_btn_nonclick)
                binding.bodyColorFiveBtn.setImageResource(R.drawable.five_btn_nonclick)
            }
            3 -> {
                binding.bodyColorThreeBtn.setImageResource(R.drawable.three_btn_click)
                binding.bodyColorTwoBtn.setImageResource(R.drawable.two_btn_nonclick)
                binding.bodyColorOneBtn.setImageResource(R.drawable.one_btn_nonclick)
                binding.bodyColorFourBtn.setImageResource(R.drawable.four_btn_nonclick)
                binding.bodyColorFiveBtn.setImageResource(R.drawable.five_btn_nonclick)
            }
            4 -> {
                binding.bodyColorFourBtn.setImageResource(R.drawable.four_btn_click)
                binding.bodyColorTwoBtn.setImageResource(R.drawable.two_btn_nonclick)
                binding.bodyColorThreeBtn.setImageResource(R.drawable.three_btn_nonclick)
                binding.bodyColorOneBtn.setImageResource(R.drawable.one_btn_nonclick)
                binding.bodyColorFiveBtn.setImageResource(R.drawable.five_btn_nonclick)
            }
            5 -> {
                binding.bodyColorFiveBtn.setImageResource(R.drawable.five_btn_click)
                binding.bodyColorTwoBtn.setImageResource(R.drawable.two_btn_nonclick)
                binding.bodyColorThreeBtn.setImageResource(R.drawable.three_btn_nonclick)
                binding.bodyColorFourBtn.setImageResource(R.drawable.four_btn_nonclick)
                binding.bodyColorOneBtn.setImageResource(R.drawable.one_btn_nonclick)
            }
        }
    }


}