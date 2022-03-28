package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_worklist.*

class WorklistActivity : AppCompatActivity() {

    var testList = arrayListOf<WorkVo>(
        WorkVo("운동이름1", "설명1", "이미지1", 0),
        WorkVo("운동이름2", "설명2", "이미지2", 1),
        WorkVo("운동이름3", "설명3", "이미지3", 2),
        WorkVo("운동이름4", "설명4", "이미지4", 3)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worklist)

        val reView = findViewById<RecyclerView>(R.id.rv)

        /*reView.adapter = WorkAdapter(this, testList)
        reView.layoutManager = LinearLayoutManager(this)
        reView.setHasFixedSize(true)
        val mAdapter = WorkAdapter(this, testList)*/

        val workSpinner = findViewById<Spinner>(R.id.workSpinner)
        val workcategory = listOf("전체", "어깨", "가슴", "배", "하체")

        val testbtn = findViewById<Button>(R.id.testbtn)
        testbtn.setOnClickListener {
            val mAdapter = WorkAdapter(this, testList)
            mAdapter.filter.filter("1")
            //reView.adapter = WorkAdapter(this, testList)
            println("~~~~~~~~~~~~worklistActivity mAdapter~~~~")
            reView.adapter=mAdapter
            println("~~~~~~~~~~~~worklistActivity mAdapterin~~~~")
            reView.layoutManager = LinearLayoutManager(this)
            reView.setHasFixedSize(true)
        }

        workSpinner.adapter = ArrayAdapter<String>(this, R.layout.item_spinner, workcategory)
        /*
        workSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                mAdapter.filter.filter("pos")
                reView.adapter=mAdapter



            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }*/


    }


}