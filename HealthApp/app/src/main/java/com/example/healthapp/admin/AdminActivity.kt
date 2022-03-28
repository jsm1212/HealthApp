package com.example.healthapp.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.healthapp.R

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val admMem = findViewById<Button>(R.id.adminMemberBtn)
        val admBbs = findViewById<Button>(R.id.adminBbsBtn)
        val admTrn = findViewById<Button>(R.id.adminTrainerBtn)

        admMem.setOnClickListener{
            val itt = Intent(this, AdminMemberActivity::class.java)
            startActivity(itt)
        }
        admBbs.setOnClickListener{
            val itt = Intent(this, AdminBbsActivity::class.java)
            startActivity(itt)
        }
        admTrn.setOnClickListener{
            val itt = Intent(this, AdminTrainerActivity::class.java)
            startActivity(itt)
        }
    }
}