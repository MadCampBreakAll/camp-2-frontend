package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentCharacterBodyShapeSelectBinding

class CharacterBodyShapeSelectFragment : Fragment() {
    private var _binding: FragmentCharacterBodyShapeSelectBinding? = null
    private val binding get() = _binding!!
    private val bodyselectfragment by lazy {CharacterBodySelectFragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBodyShapeSelectBinding.inflate(inflater, container, false)

        var character_init_binding = CharacterInitActivity.character_init_binding
        character_init_binding.userCharacterInitType.text="Body Shape"
        character_init_binding.userCharacterInitPrevBtn.visibility=View.INVISIBLE

        character_init_binding.userCharacterInitNextBtn.setOnClickListener{

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selecting_fragment, bodyselectfragment)
                .commit()
        }

        setShape(1)
        setShape(2)
        setShape(3)
        setShape(4)
        setShape(5)

        return binding.root
    }

    fun setShape(button: Int) {
        //        binding.userCharacterInit.body.setImageResource(R.drawable.five_btn_nonclick)
        var character_body_binding = CharacterInitActivity.character_init_binding

        when(button){
            1 -> {
                binding.bodyColorOneBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.body.setImageResource(R.drawable.body_shape_triangle)
                    character_body_binding.userCharacterInit.face.setImageResource(R.drawable.face_traingle)
                    character_body_binding.userCharacterInit.blush.setImageResource(R.drawable.blush_triangle)
                    clickButton(button)
                }

            }
            2 -> {
                binding.bodyColorTwoBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.body.setImageResource(R.drawable.body_shape_cloud)
                    character_body_binding.userCharacterInit.face.setImageResource(R.drawable.face_cloud)
                    character_body_binding.userCharacterInit.blush.setImageResource(R.drawable.blush_cloud)
                    clickButton(button)
                }
            }
            3 -> {
                binding.bodyColorThreeBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.body.setImageResource(R.drawable.body_shape_bean)
                    character_body_binding.userCharacterInit.face.setImageResource(R.drawable.face_bean_bread_square)
                    character_body_binding.userCharacterInit.blush.setImageResource(R.drawable.blush_bean_bread_square)
                    clickButton(button)
                }
            }
            4 -> {
                binding.bodyColorFourBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.body.setImageResource(R.drawable.body_shape_square)
                    character_body_binding.userCharacterInit.face.setImageResource(R.drawable.face_bean_bread_square)
                    character_body_binding.userCharacterInit.blush.setImageResource(R.drawable.blush_bean_bread_square)
                    clickButton(button)
                }
            }
            5 -> {
                binding.bodyColorFiveBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.body.setImageResource(R.drawable.body_shape_bread)
                    character_body_binding.userCharacterInit.face.setImageResource(R.drawable.face_bean_bread_square)
                    character_body_binding.userCharacterInit.blush.setImageResource(R.drawable.blush_bean_bread_square)
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