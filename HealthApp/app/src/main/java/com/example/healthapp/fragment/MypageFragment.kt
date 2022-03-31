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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.healthapp.login.LoginActivity
import com.example.healthapp.login.LoginMemberDao
import com.example.healthapp.R
import com.example.healthapp.mypage.*
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.user.UserApiClient

class MypageFragment(val activity:Context) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        val nickName = view.findViewById<TextView>(R.id.MypageNickname)
        val updateBtn = view.findViewById<Button>(R.id.MypageUpdateBtn)
        val logoutBtn = view.findViewById<Button>(R.id.MypageLogoutBtn)
        val routineBtn = view.findViewById<Button>(R.id.MypageRoutineBtn)
        val mywriteBtn = view.findViewById<Button>(R.id.MypageWriteBtn)
        val myreplyBtn = view.findViewById<Button>(R.id.MypageReplyBtn)
        val mylikeBtn = view.findViewById<Button>(R.id.MypageLikeBtn)

        nickName.text = LoginMemberDao.user?.nickname

        updateBtn.setOnClickListener {
            val intent = Intent(activity, MypageInformUpdateActivity::class.java)
            startActivity(intent)
        }
        logoutBtn.setOnClickListener {
            Log.d("btnclick", "로그아웃!!!")

            AlertDialog.Builder(activity)
                .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, i ->

                    // 자동로그인 정보를 가져온다.(일반회원 로그아웃 시)
                    val sp = activity.getSharedPreferences("autoLogin",Activity.MODE_PRIVATE)
                    val spEdit = sp.edit()

                    when(LoginMemberDao.user?.auth) {
                        // 카카오 유저 로그아웃
                        4->{
                            UserApiClient.instance.logout { e ->
                                if(e != null) {
                                    Toast.makeText(activity, "로그아웃 에러 $e", Toast.LENGTH_LONG).show()
                                }else{
                                    Toast.makeText(activity, "로그아웃되었습니다.", Toast.LENGTH_LONG).show()
                                    val itt = Intent(activity, LoginActivity::class.java)
                                    itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    startActivity(itt)
                                }
                            }
                        }
                        // 구글 유저 로그아웃
                        5->{
                            FirebaseAuth.getInstance().signOut()
                            Toast.makeText(activity, "로그아웃되었습니다.", Toast.LENGTH_LONG).show()
                            val itt = Intent(activity, LoginActivity::class.java)
                            itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(itt)
                        }
                        // 일반 회원 로그아웃
                        else->{
                            spEdit.clear()
                            spEdit.commit()
                            Toast.makeText(activity,"로그아웃되었습니다.",Toast.LENGTH_LONG).show()
                            val itt = Intent(activity, LoginActivity::class.java)
                            itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(itt)
                        }
                    }
                }).show()
        }
        routineBtn.setOnClickListener {
            val intent = Intent(activity, MypageRoutineActivity::class.java)
            startActivity(intent)
        }
        mywriteBtn.setOnClickListener {
            val intent = Intent(activity, MypageWriteActivity::class.java)
            startActivity(intent)
        }
        myreplyBtn.setOnClickListener {
            val intent = Intent(activity, MypageReplyActivity::class.java)
            startActivity(intent)
        }
        mylikeBtn.setOnClickListener {
            val intent = Intent(activity, MypageLikeActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}