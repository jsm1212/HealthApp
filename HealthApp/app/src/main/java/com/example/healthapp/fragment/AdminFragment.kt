package com.example.healthapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.mypage.AdapterMemberList
import com.example.healthapp.mypage.MypageDao


class AdminFragment(val activity: Context) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_admin, container, false)

        // 회원목록 연결
        val data = MypageDao.getInstance().getMemberList()
        println("확인!!!!!!!!!! $data")

        var recycleV = view.findViewById<RecyclerView>(R.id.recyMember)
        val adap = AdapterMemberList(activity, data!!)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(activity)
        recycleV.layoutManager = layout

        // 회원삭제
//        val delete = view.findViewById<Button>(R.id.amDeleteBtn)
//        delete.setOnClickListener {
//        }
        return view
    }
}
