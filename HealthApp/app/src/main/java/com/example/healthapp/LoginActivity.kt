package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthapp.workbbs.WorkActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEditId = findViewById<EditText>(R.id.loginEditId)
        val loginEditPwd = findViewById<EditText>(R.id.loginEditPwd)

        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val id = loginEditId.text.toString().trim()
            val pwd = loginEditPwd.text.toString().trim()

            val dto = LoginMemberDao.getInstance().login_M(LoginMemberDto(id,pwd,"","","",0,"","",3,""))
            if(dto != null){
                LoginMemberDao.user= dto

                Toast.makeText(this, "${dto.nickname}님 환영합니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, WorkActivity::class.java)
                startActivity(i)
            }else {
                Toast.makeText(this,"아이디나 비밀번호를 확인하세요", Toast.LENGTH_LONG).show()
            }
        }
    }
}