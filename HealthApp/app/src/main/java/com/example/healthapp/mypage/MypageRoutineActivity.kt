package com.example.healthapp.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.WorkBbsDto

// 운동 dto 추가되면 수정
class MypageRoutineActivity : AppCompatActivity() {
    var test = arrayListOf<WorkBbsDto>(
        WorkBbsDto(0, "zzz", "z닉", "제목이다이다이다이다이다", "이것ㅇㄴ 내용이다다다다",
            "2020.03.01",0, 0, 0, 0, 10, 20, "이미지경로")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_routine)

        var recycleV = findViewById<RecyclerView>(R.id.recyRoutine)

        val adap = AdapterRoutine(this, test)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout
    }
}