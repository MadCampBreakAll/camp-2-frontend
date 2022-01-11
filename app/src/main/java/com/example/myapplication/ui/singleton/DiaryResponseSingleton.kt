package com.example.myapplication.ui.singleton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.diary.dto.DiaryDto
import com.example.myapplication.api.diary.dto.GetMyDiariesResponseDto

object DiaryResponseSingleton {
    private var _getMyDiariesResponseDto: MutableLiveData<GetMyDiariesResponseDto?> =
        MutableLiveData(null)

    val getMyDiariesResponseDto: LiveData<GetMyDiariesResponseDto?> = _getMyDiariesResponseDto

    var setMyDiariesResponseDto get() = _getMyDiariesResponseDto.value
    set(value) {
        _getMyDiariesResponseDto.postValue(value)
    }

}