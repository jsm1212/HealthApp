package com.example.healthapp.mypage

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.healthapp.login.LoginMemberDao
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.R
import com.example.healthapp.Timer
import com.example.healthapp.bbs.WorkActivity

class MypageInformUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_inform_update)

        getSupportActionBar()!!.setIcon(R.drawable.appbar)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setElevation(0F)

        val updateName = findViewById<TextView>(R.id.updateName)
        val updateNick = findViewById<EditText>(R.id.updateNick)
        val updateEmail = findViewById<EditText>(R.id.updateEmail)
        val updateTel = findViewById<EditText>(R.id.updateTel)

        // 회원정보 View
        val id = LoginMemberDao.user?.id
        val data = MypageDao.getInstance().getInformation(id!!)

        updateName.text = data.name
        updateNick.setText(data.nickname)
        updateEmail.setText(data.email)
        updateTel.setText(data.tel)

        // 인증번호 창 숨기기
        val authView = findViewById<LinearLayout>(R.id.emailAuthLayout)
        authView.visibility = View.GONE

        // 이메일 인증
        var num = -1
        val emailSend = findViewById<Button>(R.id.updateEmailBtn)
        emailSend.setOnClickListener {
            authView.visibility = View.VISIBLE

            num = MypageDao.getInstance().sendEmail(updateEmail.text.toString())!!
            Toast.makeText(this, "인증메일 발송", Toast.LENGTH_LONG).show()
            println("!!!!!!!!!!인증번호" + num)

            val timer = findViewById<TextView>(R.id.emailTime)
            Timer().countDown("000500", timer)
        }
        // 이메일 인증번호 입력
        val authnum = findViewById<EditText>(R.id.emailAuthNum)
        val emailauth = findViewById<Button>(R.id.emailAuthOkBtn)
        emailauth.setOnClickListener {
            // 인증번호 미입력 및 불일치(isEmpty가 앞에 와야 함)
            if(authnum.text.isEmpty() || authnum.text.toString().toInt() != num){
                AlertDialog.Builder(this@MypageInformUpdateActivity, R.style.MyDialogTheme)
                    .setTitle("확인").setMessage("인증번호를 다시 입력해주세요.")
                    .setCancelable(false)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
                        authnum.setText("")
                    }).show()
            }else{
                Toast.makeText(this, "인증완료", Toast.LENGTH_LONG).show()
                authnum.setText("")
                authView.visibility = View.GONE
                num = 100
            }
        }

        // 정보수정 완료
        val beforeEmail = LoginMemberDao.user?.email
        val okBtn = findViewById<TextView>(R.id.updateOkBtn)
        okBtn.setOnClickListener {
            // 이메일 인증을 완료해야 수정할 수 있음
            if(beforeEmail != updateEmail.text.toString() && num != 100){
                AlertDialog.Builder(this@MypageInformUpdateActivity, R.style.MyDialogTheme)
                    .setTitle("확인").setMessage("이메일 인증을 진행해 주세요.")
                    .setCancelable(false)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
                        authnum.setText("")
                    }).show()
            }else{
                val data = LoginMemberDto(LoginMemberDao.user?.id, "", "", updateNick.text.toString(),"", 0,
                    updateEmail.text.toString(), updateTel.text.toString(),0, "", "", 0)
                println("확인!!!!!!!!!! $data")
                MypageDao.getInstance().updateMember(data)

                Toast.makeText(this, "정보 수정 완료", Toast.LENGTH_LONG).show()
                authnum.setText("")
                authView.visibility = View.GONE

                WorkActivity.selectedFragment = 3
                val intent = Intent(this, WorkActivity::class.java)
                startActivity(intent)
            }
        }
    }
}