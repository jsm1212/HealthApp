package com.example.healthapp.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.mypage.AdapterLike
import com.example.healthapp.workbbs.WorkBbsDto

class AdminBbsActivity : AppCompatActivity() {
    var test = arrayListOf<WorkBbsDto>(
        WorkBbsDto(0, "zzz", "z닉", "이것은 제목이다이다이다이다이다이것은 제목이다이다이다이다이다이것은 제목이다이다이다이다이다", "이것ㅇㄴ 내용이다다다다",
            "2020.03.01",0, 0, 0, 0, 10, 20, "이미지경로"),
        WorkBbsDto(0, "ccc", "c닉", "제목이다이다이다이다이다", "내용이다다다다",
            "2020.03.03",0, 0, 0, 0, 100, 20, "이미지경로"),
        WorkBbsDto(0, "ddd", "c닉", "다다다제목", "다다다내용",
            "2020.03.05",0, 0, 0, 0, 1000, 20, "이미지경로")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_bbs)

        var recycleV = findViewById<RecyclerView>(R.id.recyBbs)

        val adap = AdapterBbs(this, test)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout
    }
}