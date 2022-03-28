package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.calenadrBtn)

        btn.setOnClickListener {
            val i = Intent(this,CalendarActivity::class.java)
            startActivity(i)
        }
    }
}