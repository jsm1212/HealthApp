package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkBbsActivity : AppCompatActivity() {

    var emptyList = arrayListOf<WorkBbsDto>(
        WorkBbsDto(null, "", "", "등록된 게시글이 없습니다.", ".", "", 0, 0, 0, 0, 0, 0, null)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_bbs)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val workBbsList = WorkBbsDao.getInstance().getBbsList()

        if(workBbsList != null) {
            val workBbsAdapter = WorkBbsCustomAdapter(this, workBbsList)

            recyclerView.adapter = workBbsAdapter

            val layout = LinearLayoutManager(this)
            recyclerView.layoutManager = layout

            recyclerView.setHasFixedSize(true)
        }else{
            val workBbsAdapter = WorkBbsCustomAdapter(this, emptyList)

            recyclerView.adapter = workBbsAdapter

            val layout = LinearLayoutManager(this)
            recyclerView.layoutManager = layout

            recyclerView.setHasFixedSize(true)
        }
    }
}