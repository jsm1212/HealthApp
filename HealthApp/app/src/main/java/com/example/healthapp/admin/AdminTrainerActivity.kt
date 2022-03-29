package com.example.healthapp.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthapp.R

class AdminTrainerActivity : AppCompatActivity() {
    // 트레이너 dto생성되면 수정
//    var test = arrayListOf<WorkBbsDto>(
//        WorkBbsDto(0, "ccc", "c닉", "제목이다이다이다이다이다", "내용이다다다다",
//            "2020.03.03",0, 0, 0, 0, 100, 20, "이미지경로"),
//        WorkBbsDto(0, "ddd", "c닉", "다다다제목", "다다다내용",
//            "2020.03.05",0, 0, 0, 0, 1000, 20, "이미지경로")
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_trainer)

//        var recycleV = findViewById<RecyclerView>(R.id.recyTrainer)
//
//        val adap = AdapterTrainer(this, test)
//        recycleV.adapter = adap
//
//        val layout = LinearLayoutManager(this)
//        recycleV.layoutManager = layout
    }
}