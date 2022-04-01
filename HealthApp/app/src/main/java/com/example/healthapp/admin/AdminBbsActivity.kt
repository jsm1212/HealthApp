package com.example.healthapp.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDao
import com.example.healthapp.bbs.BbsDto

class AdminBbsActivity : AppCompatActivity() {
    var test = arrayListOf<BbsDto>(
        BbsDto(0, "ccc", "c닉", "제목이다이다이다이다이다", "내용이다다다다",
            "2020.03.03",0, 0, 0, 0, 100, 20, "이미지경로"),
        BbsDto(0, "ddd", "d닉", "다다다제목", "다다다내용",
            "2020.03.05",0, 0, 0, 0, 1000, 20, "이미지경로")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_bbs)

        val data = BbsDao.getInstance().getBbsList()

        var recycleV = findViewById<RecyclerView>(R.id.recyBbs)
        val adap = AdapterBbs(this, data!!)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout
    }
}