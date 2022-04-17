package com.example.healthapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.work.WorkAdapter
import com.example.healthapp.work.WorkDao
import com.example.healthapp.work.WorkDto

class WorklistFragment(val activity:Context, val applicationContext: Context) : Fragment() {

    /*var testList = arrayListOf<WorkDto>(
        WorkDto(0,"어깨 운동1", "어깨 운동1", "이미지1", 0,0),
        WorkDto(1,"가슴 운동1", "가슴 운동1", "이미지2", 1,0),
        WorkDto(2,"복부 운동1", "복부 운동1", "이미지3", 2,0),
        WorkDto(3,"하체 운동1", "하체 운동1", "이미지4", 3,0),
        WorkDto(4,"어깨 운동2", "어깨 운동2", "이미지1", 0,0),
        WorkDto(5,"가슴 운동2", "가슴 운동2", "이미지2", 1,0),
        WorkDto(6,"복부 운동2", "복부 운동2", "이미지3", 2,0),
        WorkDto(7,"하체 운동2", "하체 운동2", "이미지4", 3,0)
    )*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_worklist, container, false)

        val reView = view.findViewById<RecyclerView>(R.id.rv)

        val workSpinner = view.findViewById<Spinner>(R.id.workSpinner)
        val workcategory = listOf("전체", "어깨","가슴", "복부", "하체", "팔")

        workSpinner.adapter = ArrayAdapter<String>(activity, R.layout.item_spinner, workcategory)
        val worklist=WorkDao.getInstance().getWorkList()
        workSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                val mAdapter = WorkAdapter(activity, worklist!!)
                mAdapter.filter.filter("$pos")
                reView.adapter = WorkAdapter(applicationContext, worklist)
                println("~~~~~~~~~~~~worklistActivity mAdapter~~~~")
                reView.adapter=mAdapter
                println("~~~~~~~~~~~~worklistActivity mAdapterin~~~~")
                reView.layoutManager = LinearLayoutManager(activity)
                reView.setHasFixedSize(true)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        return view
    }
}