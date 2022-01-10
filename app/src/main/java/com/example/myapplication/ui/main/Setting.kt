package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Setting {
    data class _Setting(
        var backgroundColor: String,
        var font: Int,
        var page: Int,
    )

    private var _setting = MutableLiveData<_Setting>(
        _Setting("#FFFFFF", 0, 0)
    )

    val setting : LiveData<_Setting> get() = _setting

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

    var page
    get() = _setting.value!!.page
    set(value) {
        _setting.value!!.page = value
        _setting.postValue(_setting.value)
    }

}