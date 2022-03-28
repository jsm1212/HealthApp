package com.example.healthapp.mypage

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.healthapp.login.LoginMemberDao
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.R
import com.example.healthapp.fragment.MypageFragment

class MypageInformUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_inform_update)

        val updateId = findViewById<TextView>(R.id.updateId)
        val updateName = findViewById<TextView>(R.id.updateName)
        val updateNick = findViewById<EditText>(R.id.updateNick)
        val updateEmail = findViewById<EditText>(R.id.updateEmail)
        val updateTel = findViewById<EditText>(R.id.updateTel)

        // 정보변경 전 View
        updateId.text = LoginMemberDao.user?.id
        updateName.text = LoginMemberDao.user?.name
        updateNick.setText(LoginMemberDao.user?.nickname)
        updateEmail.setText(LoginMemberDao.user?.email)
        updateTel.setText(LoginMemberDao.user?.tel)

        // 인증번호 창 숨기기
        val emailView = findViewById<LinearLayout>(R.id.emailAuthLayout)
        val telView = findViewById<LinearLayout>(R.id.telAuthLayout)
        emailView.visibility = View.GONE
        telView.visibility = View.GONE

        // 인증유무 확인
        val beforeEmail = LoginMemberDao.user?.email
        val beforeTel = LoginMemberDao.user?.tel
        val emailFlag = false
        val telFlag = false

        // 이메일 인증
        val emailSend = findViewById<Button>(R.id.updateEmailBtn)
        emailSend.setOnClickListener {
            Log.d("btnclick", "인증메일 전송!!!")
            emailView.visibility = View.VISIBLE
        }

        // 전화번호 인증
        val telSend = findViewById<Button>(R.id.updateTelBtn)
        telSend.setOnClickListener {
            Log.d("btnclick", "인증문자 전송!!!")
            telView.visibility = View.VISIBLE
        }

        ////////// 수정 필요 -> 인증을 안해도 정보가 수정됨.
        // 정보수정 완료
        val okBtn = findViewById<TextView>(R.id.updateOkBtn)
        okBtn.setOnClickListener {
            /*if(beforeEmail != updateEmail.text.toString()){
                AlertDialog.Builder(this@MypageInformUpdateActivity)
                    .setTitle("확인").setMessage("이메일 인증을 진행해 주세요.")
                    .setCancelable(false)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
                    }).show()
            }
            if(beforeTel != updateTel.text.toString()){
                AlertDialog.Builder(this@MypageInformUpdateActivity)
                    .setTitle("확인").setMessage("전화번호 인증을 진행해 주세요.")
                    .setCancelable(false)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
                    }).show()
            }*/
            AlertDialog.Builder(this@MypageInformUpdateActivity)
                .setTitle("수정").setMessage("정보수정완료")
                .setCancelable(false)
                .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
                    MypageDao.getInstance().updateMember_M(
                        LoginMemberDto(
                        "", "", "", updateNick.text.toString(),"", 0,
                        updateEmail.text.toString(), updateTel.text.toString(),0, "", "")
                    )

                    val itt = Intent(this, MypageFragment::class.java)
                    startActivity(itt)
                }).show()
        }
    }
}