package com.example.myapplication.ui.singleton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.user.dto.GetMeResponseDto

object UserResponseSingleton {

    private var _getMeDto: MutableLiveData<GetMeResponseDto?> =
        MutableLiveData(null)

    val getMeResponseDto: LiveData<GetMeResponseDto?> = _getMeDto

    var setGetMeResponseDto get() = _getMeDto.value
        set(value) {
            _getMeDto.postValue(value)
        }

}