package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData

object Setting {
    data class _Setting(
        var backgroundColor: String,
        var font: Int,
    )

    private var _setting = MutableLiveData<_Setting>(
        _Setting("#FFFFFF", 0)
    )

    var backgroundColor
    get() =_setting.value!!.backgroundColor
    set(value) {
        _setting.value!!.backgroundColor = value
        _setting.postValue(_setting.value)
    }

    var font
    get() = _setting.value!!.font
    set(value) {
        _setting.value!!.font = value
        _setting.postValue(_setting.value)
    }
}