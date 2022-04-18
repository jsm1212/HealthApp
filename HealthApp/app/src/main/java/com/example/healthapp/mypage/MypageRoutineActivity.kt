package com.example.healthapp.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDao
import com.example.healthapp.work.WorkDto

class MypageRoutineActivity : AppCompatActivity() {
    var test = arrayListOf<WorkDto>(
        WorkDto(0, "팔굽혀펴기", "힘든운동입니다", 0, 0, "R.drawable.emtyheart")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_routine)

        getSupportActionBar()!!.setIcon(R.drawable.appbar)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setElevation(0F)

        val id = LoginMemberDao.user?.id
        val data = MypageDao.getInstance().getMyRoutine(id!!)
        println("!!!!!!!!!! 운동루틴목록 확인" + data)

        var recycleV = findViewById<RecyclerView>(R.id.recyRoutine)
        val adap = AdapterRoutine(this, data!!)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(this)
        recycleV.layoutManager = layout
    }
}