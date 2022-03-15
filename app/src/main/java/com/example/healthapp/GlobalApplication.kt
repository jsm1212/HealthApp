package com.example.healthapp

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication :Application(){
    override fun onCreate(){
        super.onCreate()

        KakaoSdk.init(this,"2b99a7bd78568245841a276608ac9563") // 네이티브 앱 키
    }
}