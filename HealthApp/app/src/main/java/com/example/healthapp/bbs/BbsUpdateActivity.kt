package com.example.healthapp.bbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthapp.R

class BbsUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbs_update)

        val data = intent.getParcelableExtra<BbsDto>("BbsInfo")

        println("게시글 수정페이지로 넘어온 id값 : "+ data?.id)

    }
}