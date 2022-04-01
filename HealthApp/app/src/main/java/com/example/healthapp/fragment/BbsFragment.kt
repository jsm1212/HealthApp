package com.example.healthapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.bbs.*

class BbsFragment(val activity:Context) : Fragment() {

    var selectSpinner = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_bbs, container, false)

        // 스피너의 값을 적용하는 함수
        fun setupSpinner(){
            val infoVal = resources.getStringArray(R.array.infoVal)
            val adapter = ArrayAdapter(activity, R.layout.item_spinner, infoVal)
            val spinner = view.findViewById<Spinner>(R.id.spinner)
            spinner.adapter = adapter
        }

        // 스피너의 해당 값 선택시 변수의 그 값을 저장
        fun choiceSpinner(){
            val spinner = view.findViewById<Spinner>(R.id.spinner)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, v: View?, position: Int, p3: Long) {
                    selectSpinner = "${spinner.getItemAtPosition(position)}"
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            println(selectSpinner)
        }

        setupSpinner()
        choiceSpinner()

        // 게시글리스트 리사이클러뷰 세팅
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val workBbsList = BbsDao.getInstance().getBbsList()
        val workBbsAdapter = WorkBbsCustomAdapter(activity, workBbsList!!)
        recyclerView.adapter = workBbsAdapter

        val layout = LinearLayoutManager(activity)
        recyclerView.layoutManager = layout

        recyclerView.setHasFixedSize(true)


        // 글쓰기 버튼
        val goWorkBbsWirte = view.findViewById<Button>(R.id.goWorkBbsWirte)

        goWorkBbsWirte.setOnClickListener {
            val intent = Intent(activity, BbsWriteActivity::class.java)
            startActivity(intent)
        }

        // 검색버튼 클릭 시 이벤트
        val WorkBbsSearchBtn = view.findViewById<Button>(R.id.WorkBbsSearchBtn)
        WorkBbsSearchBtn.setOnClickListener {
            val searchText = view.findViewById<EditText>(R.id.WorkBbsSearchbar).text.toString()
            when(selectSpinner) {
                "제목" -> {
                    val choiceTitle = BbsDao.getInstance().getBbsListSearch_M(
                        BbsParamDto("title", searchText, 0, 0, 0)
                    )
                    val workBbsAdapter = WorkBbsCustomAdapter(activity, choiceTitle)
                    recyclerView.adapter = workBbsAdapter

                    val layout = LinearLayoutManager(activity)
                    recyclerView.layoutManager = layout

                    recyclerView.setHasFixedSize(true)
                }
                "내용" -> {
                    val choiceTitle = BbsDao.getInstance().getBbsListSearch_M(
                        BbsParamDto("content", searchText, 0, 0, 0)
                    )
                    val workBbsAdapter = WorkBbsCustomAdapter(activity, choiceTitle)
                    recyclerView.adapter = workBbsAdapter

                    val layout = LinearLayoutManager(activity)
                    recyclerView.layoutManager = layout

                    recyclerView.setHasFixedSize(true)
                }
                "작성자" -> {
                    val choiceTitle = BbsDao.getInstance().getBbsListSearch_M(
                        BbsParamDto("writer", searchText, 0, 0, 0)
                    )
                    val workBbsAdapter = WorkBbsCustomAdapter(activity, choiceTitle)
                    recyclerView.adapter = workBbsAdapter

                    val layout = LinearLayoutManager(activity)
                    recyclerView.layoutManager = layout

                    recyclerView.setHasFixedSize(true)
                }
                "" -> {
                    val workBbsList = BbsDao.getInstance().getBbsList()
                    val workBbsAdapter = WorkBbsCustomAdapter(activity, workBbsList!!)
                    recyclerView.adapter = workBbsAdapter

                    val layout = LinearLayoutManager(activity)
                    recyclerView.layoutManager = layout

                    recyclerView.setHasFixedSize(true)
                }
            }
        }

        return view
    }



}