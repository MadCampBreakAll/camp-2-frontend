package com.example.myapplication.ui.singleton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.page.dto.GetDiaryInnerPagesResponse
import com.example.myapplication.api.user.dto.GetMeResponseDto

object PageResponseSingleton {
    private var _getDiaryInnerPageResponse: MutableLiveData<GetDiaryInnerPagesResponse?> =
        MutableLiveData(null)

    val getDiaryInnerPagesResponse: LiveData<GetDiaryInnerPagesResponse?> = _getDiaryInnerPageResponse

    var setGetDiaryInnerPagesResponse get() = _getDiaryInnerPageResponse.value
        set(value) {
            _getDiaryInnerPageResponse.postValue(value)
        }
}