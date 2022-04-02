package com.example.healthapp.fragment

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.admin.AdapterMember
import com.example.healthapp.admin.AdminDao
import com.example.healthapp.login.LoginMemberDao
import com.example.healthapp.mypage.MypageDao


class AdminActivity(val activity: Context) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_admin, container, false)

        // 회원목록 연결
        val data = AdminDao.getInstance().getMem_M()

        var recycleV = view.findViewById<RecyclerView>(R.id.recyMember)
        val adap = AdapterMember(activity, data!!)
        recycleV.adapter = adap

        val layout = LinearLayoutManager(activity)
        recycleV.layoutManager = layout

        // 회원삭제
        val id = LoginMemberDao.user?.id

        val delete = view.findViewById<Button>(R.id.amDeleteBtn)
        delete.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("확인").setMessage("${id}님을 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("네", DialogInterface.OnClickListener { dialog, i ->
                    val msg = MypageDao.getInstance().deleteMem_M(id!!)
                    println("확인!!!!!!!!!! $msg")

                    if(msg == "yes"){
                        Toast.makeText(activity,"삭제 완료", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(activity,"삭제 실패", Toast.LENGTH_LONG).show()
                    }
                }).show()
        }

        return view
    }
}
