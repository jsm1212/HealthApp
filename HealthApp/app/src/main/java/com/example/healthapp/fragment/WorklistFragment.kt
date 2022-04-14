package com.example.healthapp.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.OnMyKeyDown
import com.example.healthapp.R
import com.example.healthapp.work.WorkAdapter
import com.example.healthapp.work.WorkVo

class WorklistFragment(val activity:Context, val applicationContext: Context) : Fragment() {

    var testList = arrayListOf<WorkVo>(
        WorkVo("운동이름1", "설명1", "이미지1", 0),
        WorkVo("운동이름2", "설명2", "이미지2", 1),
        WorkVo("운동이름3", "설명3", "이미지3", 2),
        WorkVo("운동이름4", "설명4", "이미지4", 3)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_worklist, container, false)

        // 뒤로가기 버튼 2번 클릭시 어플 종료
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(OnMyKeyDown(activity as Activity))

        val reView = view.findViewById<RecyclerView>(R.id.rv)

        val workSpinner = view.findViewById<Spinner>(R.id.workSpinner)
        val workcategory = listOf("전체", "어깨", "가슴", "배", "하체")

        workSpinner.adapter = ArrayAdapter<String>(activity, R.layout.item_spinner, workcategory)

        workSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                val mAdapter = WorkAdapter(activity, testList)
                mAdapter.filter.filter("$pos")
                reView.adapter = WorkAdapter(applicationContext, testList)
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