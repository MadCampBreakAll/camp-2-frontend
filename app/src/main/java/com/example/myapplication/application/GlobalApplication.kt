package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.BuildConfig
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY);
    }
}