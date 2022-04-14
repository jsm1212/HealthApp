package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.healthapp.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        getSupportActionBar()!!.setIcon(R.drawable.appbar)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setElevation(0F);

        // 화면 딜레이
        val hand = Handler()
        hand.postDelayed(Runnable {
            run {
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
            }
        }, 500) // 0.5초
    }
}