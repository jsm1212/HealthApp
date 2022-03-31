package com.example.healthapp.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.BbsDto
import com.example.healthapp.work.WorkVo

// 운동루틴목록 토의 후 작성
class MypageRoutineActivity : AppCompatActivity() {
    var test = arrayListOf<WorkVo>(
        WorkVo("zzz", "운동운동", "사진", 0)
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