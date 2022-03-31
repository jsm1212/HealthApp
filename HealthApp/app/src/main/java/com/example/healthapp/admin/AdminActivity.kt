package com.example.healthapp.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.healthapp.R


class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val admMem = findViewById<Button>(R.id.adminMemberBtn)
        val admBbs = findViewById<Button>(R.id.adminBbsBtn)

        admMem.setOnClickListener{
            println("확인!!!!!!!!!! 회원관리")
            val itt = Intent(this, AdminMemberActivity::class.java)
            startActivity(itt)
        }
        admBbs.setOnClickListener{
            println("확인!!!!!!!!!! 게시물관리")
            val itt = Intent(this, AdminBbsActivity::class.java)
            startActivity(itt)
        }
    }
}
