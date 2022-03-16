package com.example.healthapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<ViewGroup>(R.id.touchView)

        view.setOnClickListener{
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }
    }
}