package com.example.healthapp.mypage

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDto
import com.example.healthapp.login.LoginMemberDao
import kotlinx.android.synthetic.main.mypage_write_layout.*

class MypageWriteActivity : AppCompatActivity() {
    var test = arrayListOf<BbsDto>(
        BbsDto(0, "ccc", "c닉", "제목이다이다이다이다이다", "내용이다다다다",
            "2020.03.03",0, 0, 0, 0, 100, 20, "이미지경로"),
        BbsDto(0, "ddd", "c닉", "다다다제목", "다다다내용",
            "2020.03.05",0, 0, 0, 0, 1000, 20, "이미지경로")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_write)

        // 내 게시글 불러오기
        val id = LoginMemberDao.user?.id
        val data = MypageDao.getInstance().getMyBbs(id!!)
        println("확인!!!!!!!!!! $id !!!!! $data")

        var recycleV = findViewById<RecyclerView>(R.id.recyWrite)
        val adap = AdapterWriter(this, data!!)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout

        // 전체 선택
        val allCheck = findViewById<CheckBox>(R.id.mywAllBtn)

//        allCheck.setOnClickListener{
//            if(allCheck.isChecked){
//                for(i in data){
//                }
//            }else{
//                for(i in 0 until data.size){
//                    mywCheck.isChecked = false
//                }
//            }
//        }

        // 선택한 목록 삭제
    }
}