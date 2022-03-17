package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_regi.*

class RegiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regi)

        val editId = findViewById<EditText>(R.id.regiIdEditText)
        val idCheck = findViewById<TextView>(R.id.regiIdCheckText)
        val editPwd = findViewById<EditText>(R.id.regiIdEditPwd)
        val editPwdCheck = findViewById<EditText>(R.id.regiIdEditPwdCheck)
        val editName = findViewById<EditText>(R.id.regiNameEditText)
        val editNickname = findViewById<EditText>(R.id.regiNicknameEditText)
        val nicknameCheck = findViewById<TextView>(R.id.regiNicknameCheckText)

        val genderRadioGroup = findViewById<RadioGroup>(R.id.regiGenderRadioGroup)

        val editAge = findViewById<EditText>(R.id.regiAgeEditText)
        val editEmail = findViewById<EditText>(R.id.regiEmailEditText)
        val emailCheck = findViewById<TextView>(R.id.regiEmailCheckText)
        val editTel = findViewById<EditText>(R.id.regiTelEditText)

        val idCheckBtn = findViewById<Button>(R.id.regiIdCheckBtn)
        val nicknameCheckBtn = findViewById<Button>(R.id.regiNicknameCheckBtn)
        val emailCheckBtn = findViewById<Button>(R.id.regiEmailCheckBtn)
        val regiBtn = findViewById<Button>(R.id.regiBtn)

        var gender:String = ""

        genderRadioGroup.setOnCheckedChangeListener{
                _, checkedId ->
            when(checkedId){
                R.id.regiManRadioBtn ->  gender= "남"
                R.id.regiWomenRadioBtn ->  gender= "여"
            }
        }

        // 아이디 중복검사
        idCheckBtn.setOnClickListener {
            val id = editId.text.toString().trim()

            val msg = LoginMemberDao.getInstance().getId_M(LoginMemberDto(id,"","","","",0,"","",0,""))

            if(msg == "n"){
                idCheck.text="이미 사용중인 아이디입니다."
                editId.setText("")
            }else{
                idCheck.text="사용 가능한 아이디입니다."
            }
        }

        // 닉네임 중복검사
        nicknameCheckBtn.setOnClickListener {
            val nickname = editNickname.text.toString().trim()

            val msg = LoginMemberDao.getInstance().checkNickname_M(LoginMemberDto("","","",nickname,"",0,"","",0,""))

            if(msg == "n"){
                nicknameCheck.text="이미 사용중인 닉네임입니다."
                editNickname.setText("")
            }else{
                nicknameCheck.text="사용 가능한 닉네임입니다."
            }
        }

        // 이메일 중복검사
        emailCheckBtn.setOnClickListener {
            val email = editEmail.text.toString().trim()

            val msg = LoginMemberDao.getInstance().checkEmail_M(LoginMemberDto("","","","","",0,email,"",0,""))

            if(msg == "n"){
                emailCheck.text="이미 사용중인 이메일입니다."
                editEmail.setText("")
            }else{
                emailCheck.text="사용 가능한 이메일입니다."
            }
        }

        // 회원가입
        regiBtn.setOnClickListener {
            val id = editId.text.toString().trim()
            val pwd = editPwd.text.toString().trim()
            val pwdCheck = editPwdCheck.text.toString().trim()
            val name = editName.text.toString().trim()
            val nickname = editNickname.text.toString().trim()

            val ageString = editAge.text.toString()
            val age:Int = ageString.toInt()

            val email = editEmail.text.toString().trim()
            val tel = editTel.text.toString().trim()

            if(pwd == pwdCheck){


                val msg = LoginMemberDao.getInstance().register_M(LoginMemberDto(id,pwd,name,nickname,gender,age,email,tel,3,""))

                if(msg == "y"){
                    Toast.makeText(this,"가입이 완료되었습니다. 로그인해주세요", Toast.LENGTH_LONG).show()

                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                }

            }else{
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }
}