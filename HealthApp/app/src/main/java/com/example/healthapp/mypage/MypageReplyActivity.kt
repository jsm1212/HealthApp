package com.example.healthapp.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDto
import com.example.healthapp.bbs.BbsReplyDto
import com.example.healthapp.login.LoginMemberDao

class MypageReplyActivity : AppCompatActivity() {
    var test = arrayListOf<BbsReplyDto>(
        BbsReplyDto(0, 1, "aaa", "a닉", "제목제목", "내용내용", "20202020",
                     0, 0, 0, 0, 10, 50, ""),
        BbsReplyDto(0, 1, "aaa", "a닉", "제목제목", "내용내용", "20202020",
            0, 0, 0, 0, 10, 50, "")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_reply)

        val id = LoginMemberDao.user?.id
        println("확인!!!!!!!!!! $id")
        val data = MypageDao.getInstance().getMyReply_M(id!!)

        var recycleV = findViewById<RecyclerView>(R.id.recyReply)
        val adap = AdapterReply(this, data!!)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout
    }
}