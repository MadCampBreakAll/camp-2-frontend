package com.example.myapplication.ui.setting.icon


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.user.UserApiService
import com.example.myapplication.databinding.ActivityCharacterInitBinding
import com.example.myapplication.databinding.UserCharacterBinding
import com.example.myapplication.util.Character
import com.example.myapplication.util.CharacterViewer
import com.example.myapplication.util.TokenManager
import com.google.gson.annotations.SerializedName
import java.util.concurrent.ConcurrentHashMap


class CharacterFixActivity : AppCompatActivity() {
    private var _binding: ActivityCharacterInitBinding? = null
    private val binding get() = _binding!!
    private val bodyshapeselectfragment by lazy { CharacterBodyShapeSelectFragment() }
    private lateinit var userApiService: UserApiService
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCharacterInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        character_init_binding = binding
        var next = binding.userCharacterInitNextBtn
        next.setColorFilter(resources.getColor(R.color.body_red))

        var prev = binding.userCharacterInitPrevBtn
        prev.setColorFilter(resources.getColor(R.color.body_red))
        prev.scaleX=-1f

        supportFragmentManager.beginTransaction()
            .replace(R.id.selecting_fragment, bodyshapeselectfragment)
            .commit()
        init()
        update()
    }

    fun init() {
        tokenManager = TokenManager(this)
        userApiService = UserApiService(tokenManager)
    }

    fun bind() {
    }

    fun update(){
        userApiService.getMe(
            success = {dto ->
                try {
                    var (_, user) = dto!!
                    var (_, _, body_shape, body_color, blush, item) = user!!
                    CharacterFixActivity.character_init_blush = blush!!
                    CharacterFixActivity.character_init_body_shape= body_shape!!
                    CharacterFixActivity.character_init_item= item!!
                    CharacterFixActivity.character_init_body_color = body_color!!
                    CharacterFixActivity.updateInitCharacter(this, binding.userCharacterInit)
                } catch (e: Throwable) {
                    Log.d("DEBUG", "ERROR")
                    e.printStackTrace()
                }
            },
            fail = null
        )
    }

    companion object {
        lateinit var character_init_binding : ActivityCharacterInitBinding
        var character_init_body_color = 1
        var character_init_body_shape = 1
        var character_init_blush = 1
        var character_init_item = 1

        fun updateInitCharacter(context: Context, binding: UserCharacterBinding){
            CharacterViewer(
                context,
                binding,
                Character(
                    CharacterFixActivity.character_init_body_shape!!,
                    CharacterFixActivity.character_init_body_color!!,
                    CharacterFixActivity.character_init_blush!!,
                    CharacterFixActivity.character_init_item!!
                ),
            ).show()
        }
    }
}