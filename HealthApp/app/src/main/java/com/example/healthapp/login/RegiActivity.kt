package com.example.healthapp.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import com.example.healthapp.R

class RegiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regi)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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

        // 비밀번호, 닉네임, 이메일, 전화번호, 정규식
        val pwdRegex = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}.$".toRegex()
        val nicknameRegex = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,8}$".toRegex()
        val emailRegex = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$".toRegex()
        val telRegex = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$".toRegex()
        val ageRegex = "\\.{3}".toRegex()

        // 성별 체크
        genderRadioGroup.setOnCheckedChangeListener{
                _, checkedId ->
            when(checkedId){
                R.id.regiManRadioBtn ->  gender= "남"
                R.id.regiWomenRadioBtn ->  gender= "여"
            }
        }



        // 아이디 중복검사
        var idChecked = false
        idCheckBtn.setOnClickListener {
            idChecked = false
            val id = editId.text.toString().trim()
            idCheck.setTextColor(Color.parseColor("#FF0000"))

            if(id != "") {

                val msg = LoginMemberDao.getInstance().getId_M(LoginMemberDto(id, "", "", "", "", 0, "", "", 0, "", "", 0))

                if (msg == "n") {
                    idCheck.text = "이미 사용중인 아이디입니다."
                    editId.setText("")
                } else {
                    idCheck.text = "사용 가능한 아이디입니다."
                    idCheck.setTextColor(Color.parseColor("#0000FF"))
                    idChecked=true
                }
            }else{
                idCheck.text="아이디를 입력해주세요."
            }

        }

        // 닉네임 중복검사
        var nicknameChecked = false
        nicknameCheckBtn.setOnClickListener {
            nicknameChecked = false
            val nickname = editNickname.text.toString().trim()
            nicknameCheck.setTextColor(Color.parseColor("#FF0000"))

            if(nickname != "" && nickname.matches(nicknameRegex)) {
                val msg = LoginMemberDao.getInstance()
                    .checkNickname_M(LoginMemberDto("", "", "", nickname, "", 0, "", "", 0, "", "", 0))

                if (msg == "n") {
                    nicknameCheck.text = "이미 사용중인 닉네임입니다."
                    editNickname.setText("")
                } else {
                    nicknameCheck.text = "사용 가능한 닉네임입니다."
                    nicknameCheck.setTextColor(Color.parseColor("#0000FF"))
                    nicknameChecked = true
                }
            }else{
                nicknameCheck.text="특수문자를 제외한 2~8자리만 가능합니다."
            }

        }

        // 이메일 중복검사
        var emailChecked = false
        emailCheckBtn.setOnClickListener {
            emailChecked=false
            val email = editEmail.text.toString().trim()
            emailCheck.setTextColor(Color.parseColor("#FF0000"))

            if(email != "" && email.matches(emailRegex)) {

                val msg = LoginMemberDao.getInstance()
                    .checkEmail_M(LoginMemberDto("", "", "", "", "", 0, email, "", 0, "", "", 0))

                if (msg == "n") {
                    emailCheck.text = "이미 사용중인 이메일입니다."
                    editEmail.setText("")
                } else {
                    emailCheck.text = "사용 가능한 이메일입니다."
                    emailCheck.setTextColor(Color.parseColor("#0000FF"))
                    emailChecked = true
                }
            }else {
                emailCheck.text = "이메일 형식이 올바르지 않습니다."
            }
        }

        // 회원가입
        regiBtn.setOnClickListener {
            val id = editId.text.toString().trim()
            val pwd = editPwd.text.toString().trim()
            val pwdCheck = editPwdCheck.text.toString().trim()
            val name = editName.text.toString().trim()
            val nickname = editNickname.text.toString().trim()

            val ageString = editAge.text.toString().trim()
            var age: Int = 0

            val email = editEmail.text.toString().trim()
            val tel = editTel.text.toString().trim()

            val idCheckResp = LoginMemberDao.getInstance()
                .getId_M(LoginMemberDto(id, "", "", "", "", 0, "", "", 0, "", "", 0))
            val nickNameCheckResp = LoginMemberDao.getInstance()
                .checkNickname_M(LoginMemberDto("", "", "", nickname, "", 0, "", "", 0, "", "", 0))
            val emailCheckResp = LoginMemberDao.getInstance()
                .checkEmail_M(LoginMemberDto("", "", "", "", "", 0, email, "", 0, "", "", 0))

            if(gender == ""){
                Toast.makeText(this, "성별을 선택해주세요.", Toast.LENGTH_LONG).show()
            } else if (ageString == "") {
                Toast.makeText(this, "나이를 입력해주세요.", Toast.LENGTH_LONG).show()
            } else {
                age = ageString.toInt()
                if (!idChecked || idCheckResp == "n" || id == "") {
                    Toast.makeText(this, "아이디 확인을 다시 진행해주세요", Toast.LENGTH_LONG).show()
                    idCheck.text = ""
                } else if (!nicknameChecked || nickNameCheckResp == "n" || nickname == "") {
                    Toast.makeText(this, "닉네임 확인을 다시 진행해주새요", Toast.LENGTH_LONG).show()
                    nicknameCheck.text = ""
                } else if (!emailChecked || emailCheckResp == "n" || email == "") {
                    Toast.makeText(this, "이메일 확인을 다시 진행해주세요.", Toast.LENGTH_LONG).show()
                    emailCheck.text = ""
                } else {
                    if (!pwd.matches(pwdRegex)) {
                        Toast.makeText(this, "비밀번호는 문자, 숫자, 특수문자를 포함하여 8자 이상만 가능합니다.", Toast.LENGTH_LONG).show()
                    } else if (!nickname.matches(nicknameRegex)) {
                        Toast.makeText(this, "닉네임은 특수문자를 제외한 2~8자리만 가능합니다.", Toast.LENGTH_LONG).show()
                        nicknameCheck.text = "특수문자를 제외한 2~8자리만 가능합니다."
                        nicknameCheck.setTextColor(Color.parseColor("#FF0000"))
                    } else if (!email.matches(emailRegex)) {
                        Toast.makeText(this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_LONG).show()
                        emailCheck.text = "이메일 형식이 올바르지 않습니다."
                        emailCheck.setTextColor(Color.parseColor("#FF0000"))
                    } else if (!tel.matches(telRegex)) {
                        Toast.makeText(this, "휴대폰번호 형식이 올바르지 않습니다.", Toast.LENGTH_LONG).show()
                    } else {
                        if (pwd == pwdCheck) {

                            val msg = LoginMemberDao.getInstance()
                                .register_M(LoginMemberDto(id, pwd, name, nickname, gender, age, email, tel, 3, "", "", 0))

                            if (msg == "y") {
                                Toast.makeText(this, "가입이 완료되었습니다. 로그인해주세요", Toast.LENGTH_LONG).show()
                                val i = Intent(this, LoginActivity::class.java)
                                startActivity(i)
                            } else {
                                Toast.makeText(this, "$msg", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }


    }
}