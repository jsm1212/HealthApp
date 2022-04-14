package com.example.healthapp

import android.app.Activity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlin.system.exitProcess

class OnMyKeyDown(val activity: Activity) : View.OnKeyListener {
    private var backPressedTime: Long = 0

    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        Log.wtf("확인하고싶음", p2.toString())
        if(p1 == KeyEvent.KEYCODE_BACK && p2?.action == KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis() - backPressedTime >= 1500){
                // 처음 클릭 메시지
                backPressedTime = System.currentTimeMillis()
                Toast.makeText(activity, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
            }else{
                ActivityCompat.finishAffinity(activity as Activity)
                System.runFinalization()
                exitProcess(0)
            }
            return true
        }
        return false
    }
}