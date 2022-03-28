package com.example.healthapp.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.R

// 회원 신고수 추가되면 수정
class AdminMemberActivity : AppCompatActivity() {
    var test = arrayListOf<LoginMemberDto>(
        LoginMemberDto("aaa", "aaa", "AAA", "에이","여", 30,
                    "aaa@naver.com", "010-1111-1111",0, "2020.02.02", ""),
        LoginMemberDto("bbb", "bbb", "BBB", "비비비","남", 30,
            "bbb@naver.com", "010-2222-2222",0, "2030.03.03","")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_member)

        var recycleV = findViewById<RecyclerView>(R.id.recyMember)

        val adap = AdapterMember(this, test)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout
    }
}