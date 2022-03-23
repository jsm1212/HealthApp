package com.example.healthapp.mypage

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.healthapp.LoginMemberDao
import com.example.healthapp.LoginMemberDto
import com.example.healthapp.R

class MypageInformUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_inform_update)

        val updateId = findViewById<TextView>(R.id.updateId)
        val updateName = findViewById<TextView>(R.id.updateName)
        val updateNick = findViewById<EditText>(R.id.updateNick)
        val updateEmail = findViewById<EditText>(R.id.updateEmail)
        val updateTel = findViewById<EditText>(R.id.updateTel)

        // 정보변경 전 View
        updateId.text = LoginMemberDao.user?.id
        updateName.text = LoginMemberDao.user?.name
        updateNick.setText(LoginMemberDao.user?.nickname)
        updateEmail.setText(LoginMemberDao.user?.email)
        updateTel.setText(LoginMemberDao.user?.tel)
    }
}