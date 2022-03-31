package com.example.healthapp.admin

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDao

class AdminMemberActivity(val activity: Context) : AppCompatActivity() {
    var test = arrayListOf<LoginMemberDto>(
        LoginMemberDto("aaa", "aaa", "AAA", "에이","여", 30,
                    "aaa@naver.com", "010-1111-1111",0, "2020.02.02", ""),
        LoginMemberDto("bbb", "bbb", "BBB", "비비비","남", 30,
            "bbb@naver.com", "010-2222-2222",0, "2030.03.03","")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_member)

        // 회원목록 연결
//        val data = AdminDao.getInstance().getAdminMem_M()

        var recycleV = findViewById<RecyclerView>(R.id.recyMember)
        val adap = AdapterMember(this, test)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout

        // 회원삭제
        val nick = LoginMemberDao.user?.nickname

        val delete = findViewById<Button>(R.id.amDeleteBtn)
        delete.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("확인").setMessage("닉네임 ${nick}님을 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("네", DialogInterface.OnClickListener { dialog, i ->
                    val msg = AdminDao.getInstance().deleteMem_M(nick!!)
                    println("확인!!!!!!!!!! $msg")

                    if(msg == "yes"){
                        Toast.makeText(this,"삭제 완료", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"삭제 실패", Toast.LENGTH_LONG).show()
                    }
                }).show()
        }
    }
}