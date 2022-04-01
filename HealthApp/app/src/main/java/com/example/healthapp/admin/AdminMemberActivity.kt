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

class AdminMemberActivity : AppCompatActivity() {
    var test = arrayListOf<LoginMemberDto>(
        LoginMemberDto("aaa", "aaa", "AAA", "에이","여", 30,
                    "aaa@naver.com", "010-1111-1111",0, "2020.02.02", "", 0),
        LoginMemberDto("bbb", "bbb", "BBB", "비비비","남", 30,
            "bbb@naver.com", "010-2222-2222",0, "2030.03.03","", 0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_member)

        // 회원목록 연결
        val data = AdminDao.getInstance().getMem_M()

        var recycleV = findViewById<RecyclerView>(R.id.recyMember)
        val adap = AdapterMember(this, data!!)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout

        // 회원삭제
        val id = LoginMemberDao.user?.id

        val delete = findViewById<Button>(R.id.amDeleteBtn)
        delete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("확인").setMessage("${id}님을 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("네", DialogInterface.OnClickListener { dialog, i ->
                    val msg = AdminDao.getInstance().deleteMem_M(id!!)
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