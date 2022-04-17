package com.example.healthapp.fragment

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.healthapp.login.LoginActivity
import com.example.healthapp.login.LoginMemberDao
import com.example.healthapp.mypage.AdapterMemberList
import com.example.healthapp.mypage.MypageDao
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.user.UserApiClient


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

        val logout = view.findViewById<Button>(R.id.adminLogout)
        // 로그아웃
        logout.setOnClickListener {
            print("!!!!!!!!!! 관리자 로그아웃")

            AlertDialog.Builder(activity, R.style.MyDialogTheme)
                .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, i ->
                    // 자동로그인 정보
                    val sp = activity.getSharedPreferences("autoLogin", Activity.MODE_PRIVATE)
                    val spEdit = sp.edit()
                    spEdit.clear()
                    spEdit.commit()
                    Toast.makeText(activity, "로그아웃 완료", Toast.LENGTH_LONG).show()

                    val itt = Intent(activity, LoginActivity::class.java)
                    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(itt)
                }).setNegativeButton("취소"){_, _ -> }.show()
        }
        return view
    }
}
