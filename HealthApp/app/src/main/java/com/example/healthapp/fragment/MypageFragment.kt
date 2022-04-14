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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.healthapp.OnMyKeyDown
import com.example.healthapp.R
import com.example.healthapp.login.LoginActivity
import com.example.healthapp.login.LoginMemberDao
import com.example.healthapp.mypage.*
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.user.UserApiClient

class MypageFragment(val activity:Context) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        // 뒤로가기 버튼 2번 클릭시 어플 종료
        view.isFocusableInTouchMode=true
        view.requestFocus()
        view.setOnKeyListener(OnMyKeyDown(activity as Activity))

        val nickName = view.findViewById<TextView>(R.id.MypageNickname)
        val updateBtn = view.findViewById<Button>(R.id.MypageUpdateBtn)
        val logoutBtn = view.findViewById<Button>(R.id.MypageLogoutBtn)
        val routineBtn = view.findViewById<Button>(R.id.MypageRoutineBtn)
        val mywriteBtn = view.findViewById<Button>(R.id.MypageWriteBtn)
        val myreplyBtn = view.findViewById<Button>(R.id.MypageReplyBtn)
        val mylikeBtn = view.findViewById<Button>(R.id.MypageLikeBtn)
        val memDelete = view.findViewById<TextView>(R.id.memberDelete)

        // 닉네임 호출
        val id = LoginMemberDao.user?.id
        val data = MypageDao.getInstance().getInformation(id!!)
        nickName.text = data.nickname

        // 정보수정으로 이동
        updateBtn.setOnClickListener {
            val intent = Intent(activity, MypageInformUpdateActivity::class.java)
            startActivity(intent)
        }

        // 로그아웃
        logoutBtn.setOnClickListener {
            Log.d("btnclick", "로그아웃!!!")

            AlertDialog.Builder(activity, R.style.MyDialogTheme)
                .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, i ->

                    // 자동로그인 정보를 가져온다.(일반회원 로그아웃 시)
                    val sp = activity.getSharedPreferences("autoLogin", Activity.MODE_PRIVATE)
                    val spEdit = sp.edit()

                    when (LoginMemberDao.user?.auth) {
                        // 카카오 유저 로그아웃
                        4 -> {
                            UserApiClient.instance.logout { e ->
                                if (e != null) {
                                    Toast.makeText(activity, "로그아웃 에러 $e", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(activity, "로그아웃되었습니다.", Toast.LENGTH_LONG).show()
                                    val itt = Intent(activity, LoginActivity::class.java)
                                    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    startActivity(itt)
                                }
                            }
                        }
                        // 구글 유저 로그아웃
                        5 -> {
                            FirebaseAuth.getInstance().signOut()
                            Toast.makeText(activity, "로그아웃되었습니다.", Toast.LENGTH_LONG).show()
                            val itt = Intent(activity, LoginActivity::class.java)
                            itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(itt)
                        }
                        // 일반 회원 로그아웃
                        else -> {
                            spEdit.clear()
                            spEdit.commit()
                            Toast.makeText(activity, "로그아웃되었습니다.", Toast.LENGTH_LONG).show()
                            val itt = Intent(activity, LoginActivity::class.java)
                            itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(itt)
                        }
                    }
                }).setNegativeButton("취소"){_, _ -> }.show()
        }

        // 루틴목록으로 이동
        routineBtn.setOnClickListener {
            val intent = Intent(activity, MypageRoutineActivity::class.java)
            startActivity(intent)
        }

        // 내 게시글로 이동
        mywriteBtn.setOnClickListener {
            val intent = Intent(activity, MypageWriteActivity::class.java)
            startActivity(intent)
        }

        // 내 댓글로 이동
        myreplyBtn.setOnClickListener {
            val intent = Intent(activity, MypageReplyActivity::class.java)
            startActivity(intent)
        }

        // 좋아요 누른 게시글로 이동
        mylikeBtn.setOnClickListener {
            val intent = Intent(activity, MypageLikeActivity::class.java)
            startActivity(intent)
        }

        // 회원탈퇴
        memDelete.setOnClickListener {
            AlertDialog.Builder(activity, R.style.MyDialogTheme)
                .setTitle("경고").setMessage("모든 회원정보가 사라집니다.\n그래도 탈퇴하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("네", DialogInterface.OnClickListener { dialog, i ->
                    val msg = MypageDao.getInstance().deleteMember(id!!)
                    println("확인!!!!!!!!!! $msg")

                    if (msg == "yes") {
                        Toast.makeText(activity, "회원탈퇴완료", Toast.LENGTH_LONG).show()
                        val intent = Intent(activity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(activity, "다시 시도해 주십시오.", Toast.LENGTH_LONG).show()
                    }
                }).setNegativeButton("취소"){_, _ -> }.show()
        }
        return view
    }

    fun refresh(){
        val re = fragmentManager?.beginTransaction()
        re!!.detach(this).detach(this).commit()
    }
}