package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.api.auth.AuthApiProvider
import com.example.myapplication.api.auth.AuthApiService
import com.example.myapplication.api.dto.RegisterRequestDto
import com.example.myapplication.api.user.UserApiProvider
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.databinding.FragmentCharacterBodyShapeSelectBinding
import com.example.myapplication.databinding.FragmentCharacterItemBinding

class CharacterItemSelectFragment : Fragment() {
    private var _binding: FragmentCharacterItemBinding? = null
    private val binding get() = _binding!!
    private val blushselectfragment by lazy { CharacterBlushFragment() }
    private var authApiProvider: AuthApiProvider? = null;
    private var tokenManager: TokenManager? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(requireContext().applicationContext);
        authApiProvider = AuthApiService(tokenManager!!).getProvider();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterItemBinding.inflate(inflater, container, false)
        var character_init_binding = CharacterInitActivity.character_init_binding
        character_init_binding.userCharacterInitType.text = "Item"
        character_init_binding.userCharacterInitFirstBar.setImageResource(R.drawable.init_rest_steps)
        character_init_binding.userCharacterInitSecondBar.setImageResource(R.drawable.init_rest_steps)
        character_init_binding.userCharacterInitThirdBar.setImageResource(R.drawable.init_rest_steps)
        character_init_binding.userCharacterInitForthBar.setImageResource(R.drawable.init_current_step)

        var next = character_init_binding.userCharacterInitNextBtn
        next.setImageResource(R.drawable.start)
        next.setColorFilter(resources.getColor(R.color.body_red))
        next.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            val registerRequestDto = RegisterRequestDto(
                tokenManager!!.getAccessToken(),
                nickname = "TEST",
                body = CharacterInitActivity.character_init_body_shape,
                bodyColor = CharacterInitActivity.character_init_body_color,
                font = 0,
                item = CharacterInitActivity.character_init_item,
                blushColor = CharacterInitActivity.character_init_blush,
            );
            createCharacter(registerRequestDto);
            startActivity(intent)
        }

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
                    CharacterInitActivity.character_init_item = 1
                    clickButton(button)
                }

            }
            2 -> {
                binding.bodyColorTwoBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("ribbon"))
                    CharacterInitActivity.character_init_item = 2
                    clickButton(button)
                }
            }
            3 -> {
                binding.bodyColorThreeBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("crown"))
                    CharacterInitActivity.character_init_item = 3
                    clickButton(button)
                }
            }
            4 -> {
                binding.bodyColorFourBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("merong"))
                    CharacterInitActivity.character_init_item = 4
                    clickButton(button)
                }
            }
            5 -> {
                binding.bodyColorFiveBtn.setOnClickListener {
                    character_body_binding.userCharacterInit.item.visibility = View.VISIBLE
                    character_body_binding.userCharacterInit.item.setImageResource(item_kind("glasses"))
                    CharacterInitActivity.character_init_item = 5
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

    fun createCharacter(registerRequestDto: RegisterRequestDto){
        authApiProvider!!.register(registerRequestDto);
    }
}