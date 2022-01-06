package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentCharacterBodyShapeSelectBinding
import com.example.myapplication.databinding.FragmentCharacterItemBinding

class CharacterItemSelectFragment : Fragment() {
    private var _binding: FragmentCharacterItemBinding? = null
    private val binding get() = _binding!!
    private val blushselectfragment by lazy { CharacterBlushFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterItemBinding.inflate(inflater, container, false)
        var character_init_binding = CharacterInitActivity.character_init_binding
        character_init_binding.userCharacterInitType.text = "Item"

        var next = character_init_binding.userCharacterInitNextBtn
        next.setImageResource(R.drawable.start)
        next.setColorFilter(resources.getColor(R.color.body_pink))

        var prev = character_init_binding.userCharacterInitPrevBtn
        prev.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selecting_fragment, blushselectfragment)
                .commit()
        }

        setItem(1)
        setItem(2)
        setItem(3)
        setItem(4)
        setItem(5)

        return binding.root
    }

    fun setItem(button: Int) {
        var character_body_binding = CharacterInitActivity.character_init_binding
        when (button) {
            1 -> {
                binding.bodyColorOneBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.INVISIBLE
                    clickButton(button)
                }

            }
            2 -> {
                binding.bodyColorTwoBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("ribbon"))
                    clickButton(button)
                }
            }
            3 -> {
                binding.bodyColorThreeBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("crown"))
                    clickButton(button)
                }
            }
            4 -> {
                binding.bodyColorFourBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("merong"))
                    clickButton(button)
                }
            }
            5 -> {
                binding.bodyColorFiveBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("glasses"))
                    clickButton(button)
                }
            }
        }

    }

    fun clickButton(button: Int) {
        var i = 0
        when (button) {
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
        var shape = CharacterInitActivity.character_init_body_shape
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


}