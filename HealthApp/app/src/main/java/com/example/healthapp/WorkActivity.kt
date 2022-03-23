package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.healthapp.admin.AdminActivity
import com.example.healthapp.mypage.MypageActivity

class WorkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        val btn1 = findViewById<Button>(R.id.goToBbs)
        val btn2 = findViewById<Button>(R.id.goToMypage)
        val btn3 = findViewById<Button>(R.id.goToAdmin)

        btn1.setOnClickListener {
            val intent = Intent(this, WorkBbsActivity::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
    }
}