package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WorkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        val btn = findViewById<Button>(R.id.goToBbs)

        btn.setOnClickListener {
            val intent = Intent(this, WorkBbsActivity::class.java)
            startActivity(intent)
        }


    }
}