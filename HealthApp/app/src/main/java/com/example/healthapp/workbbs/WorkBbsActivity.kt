package com.example.healthapp.workbbs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R

class WorkBbsActivity : AppCompatActivity() {

    var emptyList = arrayListOf<WorkBbsDto>(
        WorkBbsDto(null, "", "", "등록된 게시글이 없습니다.", ".", "", 0, 0, 0, 0, 0, 0, null)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_bbs)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val workBbsList = WorkBbsDao.getInstance().getBbsList()

        val workBbsAdapter = WorkBbsCustomAdapter(this, workBbsList!!)

        recyclerView.adapter = workBbsAdapter

        val layout = LinearLayoutManager(this)
        recyclerView.layoutManager = layout

        recyclerView.setHasFixedSize(true)

        val goWorkBbsWirte = findViewById<Button>(R.id.goWorkBbsWirte)

        goWorkBbsWirte.setOnClickListener {
            val intent = Intent(this, WorkBbsWriteActivity::class.java)
            startActivity(intent)
        }

    }
}