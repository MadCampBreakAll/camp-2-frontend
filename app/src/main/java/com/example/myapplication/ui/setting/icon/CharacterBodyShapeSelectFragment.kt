package com.example.myapplication.ui.setting.icon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCharacterBodyShapeSelectBinding

class CharacterBodyShapeSelectFragment : Fragment() {
    private var _binding: FragmentCharacterBodyShapeSelectBinding? = null
    private val binding get() = _binding!!
    private val bodyselectfragment by lazy { CharacterBodySelectFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBodyShapeSelectBinding.inflate(inflater, container, false)

        var character_init_binding = CharacterFixActivity.character_init_binding
        character_init_binding.userCharacterInitType.text="Body Shape"
        character_init_binding.userCharacterInitPrevBtn.visibility=View.INVISIBLE
        character_init_binding.userCharacterInitFirstBar.setImageResource(R.drawable.init_current_step)
        character_init_binding.userCharacterInitSecondBar.setImageResource(R.drawable.init_rest_steps)
        character_init_binding.userCharacterInitThirdBar.setImageResource(R.drawable.init_rest_steps)
        character_init_binding.userCharacterInitForthBar.setImageResource(R.drawable.init_rest_steps)

        var next = character_init_binding.userCharacterInitNextBtn
        next.setImageResource(R.drawable.character_init_next_btn)
        next.setOnClickListener{

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
        var character_body_binding = CharacterFixActivity.character_init_binding.userCharacterInit

        when(button){
            1 -> {
                binding.bodyColorOneBtn.setOnClickListener {
                    CharacterFixActivity.character_init_body_shape = 1
                    character_body_binding.body.setImageResource(R.drawable.body_shape_triangle)
                    character_body_binding.face.setImageResource(R.drawable.face_traingle)
                    character_body_binding.blush.setImageResource(R.drawable.blush_triangle)

                    item_changed_following_shape()
                    clickButton(button)
                }

            }
            2 -> {
                binding.bodyColorTwoBtn.setOnClickListener {
                    CharacterFixActivity.character_init_body_shape = 2
                    character_body_binding.body.setImageResource(R.drawable.body_shape_cloud)
                    character_body_binding.face.setImageResource(R.drawable.face_cloud)
                    character_body_binding.blush.setImageResource(R.drawable.blush_cloud)

                    item_changed_following_shape()
                    clickButton(button)
                }
            }
            3 -> {
                binding.bodyColorThreeBtn.setOnClickListener {
                    CharacterFixActivity.character_init_body_shape = 3
                    character_body_binding.body.setImageResource(R.drawable.body_shape_bean)
                    character_body_binding.face.setImageResource(R.drawable.face_bean_bread_square)
                    character_body_binding.blush.setImageResource(R.drawable.blush_bean_bread_square)

                    item_changed_following_shape()
                    clickButton(button)
                }
            }
            4 -> {
                binding.bodyColorFourBtn.setOnClickListener {
                    CharacterFixActivity.character_init_body_shape = 4
                    character_body_binding.body.setImageResource(R.drawable.body_shape_square)
                    character_body_binding.face.setImageResource(R.drawable.face_bean_bread_square)
                    character_body_binding.blush.setImageResource(R.drawable.blush_bean_bread_square)

                    item_changed_following_shape()
                    clickButton(button)
                }
            }
            5 -> {
                binding.bodyColorFiveBtn.setOnClickListener {
                    CharacterFixActivity.character_init_body_shape = 5
                    character_body_binding.body.setImageResource(R.drawable.body_shape_bread)
                    character_body_binding.face.setImageResource(R.drawable.face_bean_bread_square)
                    character_body_binding.blush.setImageResource(R.drawable.blush_bean_bread_square)

                    item_changed_following_shape()
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

    fun item_kind(item: String): Int {
        var shape = CharacterFixActivity.character_init_body_shape
        var result_item = 0
        when (shape) {
            1 -> {
                when (item) {
                    "ribbon" -> result_item = R.drawable.item_ribbon_triangle
                    "crown" -> result_item = R.drawable.item_crown_triangle
                    "merong" -> result_item = R.drawable.item_merong_triangle
                    "glasses" -> result_item = R.drawable.item_glasses_triangle
                }
            }
            2 -> {
                when (item) {
                    "ribbon" -> result_item = R.drawable.item_ribbon_cloud
                    "crown" -> result_item = R.drawable.item_crown_cloud
                    "merong" -> result_item = R.drawable.item_merong_cloud
                    "glasses" -> result_item = R.drawable.item_glasses_cloud
                }
            }
            else -> {
                when (item) {
                    "ribbon" -> result_item = R.drawable.item_ribbon_bean_bread_square
                    "crown" -> result_item = R.drawable.item_crown_bean_bread_square
                    "merong" -> result_item = R.drawable.item_merong_bean_bread_square
                    "glasses" -> result_item = R.drawable.item_glasses_bean_bread_square
                }
            }
        }
        return result_item
    }

    fun item_string(item_num: Int): String{
        var item_str = ""
        when(item_num){
            2 -> item_str = "ribbon"
            3 -> item_str = "crown"
            4 -> item_str = "merong"
            5 -> item_str = "glasses"
        }
        return item_str
    }

    fun item_changed_following_shape(){
        var character_body_binding = CharacterFixActivity.character_init_binding.userCharacterInit
        var item_num = CharacterFixActivity.character_init_item
        if (item_num == 1) {
            character_body_binding.item.visibility=View.INVISIBLE
        }
        else {
            character_body_binding.item.visibility=View.VISIBLE
            character_body_binding.item.setImageResource(item_kind(item_string(item_num)))
        }
    }
}