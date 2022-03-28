package com.example.healthapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.work.WorkAdapter
import com.example.healthapp.work.WorkVo

class WorklistFragment(val activity:Context) : Fragment() {

    var testList = arrayListOf<WorkVo>(
        WorkVo("운동이름1", "설명1", "이미지1", 0),
        WorkVo("운동이름2", "설명2", "이미지2", 1),
        WorkVo("운동이름3", "설명3", "이미지3", 2),
        WorkVo("운동이름4", "설명4", "이미지4", 3)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_worklist, container, false)

        val reView = view.findViewById<RecyclerView>(R.id.rv)

        /*reView.adapter = WorkAdapter(this, testList)
        reView.layoutManager = LinearLayoutManager(this)
        reView.setHasFixedSize(true)
        val mAdapter = WorkAdapter(this, testList)*/

        val workSpinner = view.findViewById<Spinner>(R.id.workSpinner)
        val workcategory = listOf("전체", "어깨", "가슴", "배", "하체")

        val testbtn = view.findViewById<Button>(R.id.testbtn)
        testbtn.setOnClickListener {
            val mAdapter = WorkAdapter(activity, testList)
            mAdapter.filter.filter("1")
            //reView.adapter = WorkAdapter(this, testList)
            println("~~~~~~~~~~~~worklistActivity mAdapter~~~~")
            reView.adapter=mAdapter
            println("~~~~~~~~~~~~worklistActivity mAdapterin~~~~")
            reView.layoutManager = LinearLayoutManager(activity)
            reView.setHasFixedSize(true)
        }

        workSpinner.adapter = ArrayAdapter<String>(activity, R.layout.item_spinner, workcategory)
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


        return view
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_worklist)
//
//        val reView = findViewById<RecyclerView>(R.id.rv)
//
//        /*reView.adapter = WorkAdapter(this, testList)
//        reView.layoutManager = LinearLayoutManager(this)
//        reView.setHasFixedSize(true)
//        val mAdapter = WorkAdapter(this, testList)*/
//
//        val workSpinner = findViewById<Spinner>(R.id.workSpinner)
//        val workcategory = listOf("전체", "어깨", "가슴", "배", "하체")
//
//        val testbtn = findViewById<Button>(R.id.testbtn)
//        testbtn.setOnClickListener {
//            val mAdapter = WorkAdapter(this, testList)
//            mAdapter.filter.filter("1")
//            //reView.adapter = WorkAdapter(this, testList)
//            println("~~~~~~~~~~~~worklistActivity mAdapter~~~~")
//            reView.adapter=mAdapter
//            println("~~~~~~~~~~~~worklistActivity mAdapterin~~~~")
//            reView.layoutManager = LinearLayoutManager(this)
//            reView.setHasFixedSize(true)
//        }
//
//        workSpinner.adapter = ArrayAdapter<String>(this, R.layout.item_spinner, workcategory)
//        /*
//        workSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
//                mAdapter.filter.filter("pos")
//                reView.adapter=mAdapter
//            }
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }*/
//    }
}