package com.example.healthapp.mypage

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.healthapp.LoginActivity
import com.example.healthapp.LoginMemberDao
import com.example.healthapp.R

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val nickName = findViewById<TextView>(R.id.MypageNickname)
        val updateBtn = findViewById<Button>(R.id.MypageUpdateBtn)
        val logoutBtn = findViewById<Button>(R.id.MypageLogoutBtn)
        val routineBtn = findViewById<Button>(R.id.MypageRoutineBtn)
        val mywriteBtn = findViewById<Button>(R.id.MypageWriteBtn)
        val myreplyBtn = findViewById<Button>(R.id.MypageReplyBtn)
        val mylikeBtn = findViewById<Button>(R.id.MypageLikeBtn)

        nickName.text = LoginMemberDao.user?.nickname

        updateBtn.setOnClickListener {
            val intent = Intent(this, MypageInformUpdateActivity::class.java)
            startActivity(intent)
        }
        logoutBtn.setOnClickListener {
            Log.d("btnclick", "로그아웃!!!")

            AlertDialog.Builder(this@MypageActivity)
                .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, i ->
                    val itt = Intent(this, LoginActivity::class.java)
                    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(itt)
                }).show()
        }
        routineBtn.setOnClickListener {
            val intent = Intent(this, MypageRoutineActivity::class.java)
            startActivity(intent)
        }
        mywriteBtn.setOnClickListener {
            val intent = Intent(this, MypageWriteActivity::class.java)
            startActivity(intent)
        }
        myreplyBtn.setOnClickListener {
            val intent = Intent(this, MypageReplyActivity::class.java)
            startActivity(intent)
        }
        mylikeBtn.setOnClickListener {
            val intent = Intent(this, MypageLikeActivity::class.java)
            startActivity(intent)
        }
    }
}