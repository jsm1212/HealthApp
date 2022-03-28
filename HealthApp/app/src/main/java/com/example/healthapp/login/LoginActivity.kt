package com.example.healthapp.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.healthapp.R
import com.example.healthapp.workbbs.WorkActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.*
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val googleSignIntent by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        GoogleSignIn.getClient(this, gso).signInIntent
    }

    companion object {
        const val RESULT_CODE =10
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEditId = findViewById<EditText>(R.id.loginEditId)
        val loginEditPwd = findViewById<EditText>(R.id.loginEditPwd)

        val loginBtn = findViewById<Button>(R.id.loginBtn)

        // 일반회원 로그인
        loginBtn.setOnClickListener {
            val id = loginEditId.text.toString().trim()
            val pwd = loginEditPwd.text.toString().trim()

            val dto = LoginMemberDao.getInstance().login_M(LoginMemberDto(id, pwd, "", "", "", 0, "", "", 3, "",""))
            if (dto != null) {
                LoginMemberDao.user = dto

                Toast.makeText(this, "${dto.nickname}님 환영합니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, WorkActivity::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this, "아이디나 비밀번호를 확인하세요", Toast.LENGTH_LONG).show()
            }
        }
        // 카카오 API 로그인 에러처리
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 되었습니다(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱입니다", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태입니다", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류입니다", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID입니다", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않습니다(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러입니다", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없습니다", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러입니다", Toast.LENGTH_SHORT).show()
                        Log.d("~~~~error", error.toString())
                    }
                }
            }

            // 에러가 아닐경우 로그인 정보를 받아서 간접적으로 로그인시킨다.
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                UserApiClient.instance.me { user, error ->
                    if(error!=null){
                        Log.e(TAG,"사용자 정보 요청 실패", error)
                    }
                    else if(user != null) {
                        Log.i(TAG, "사용자 정보 요청 성공+${user.id}")
                        Log.i(TAG, "사용자 정보 요청 성공+${user.kakaoAccount!!.profile!!.nickname}")

                        val kakaoName = user.kakaoAccount!!.profile!!.nickname
                        val kakaoNum = user.id.toString()

                        val idmsg = LoginMemberDao.getInstance()
                            .getId_M(LoginMemberDto(kakaoName,kakaoNum,kakaoName,kakaoName,"?",0,"?","?",4,"",""))

                        if(idmsg == "n"){
                            val dto = LoginMemberDao.getInstance()
                                .login_M(LoginMemberDto(kakaoName,kakaoNum,kakaoName,kakaoName,"?",0,"?","?",4,"",""))
                            if(dto != null) {
                                LoginMemberDao.user = dto
                            }
                        }else{
                            val msg = LoginMemberDao.getInstance()
                                .register_M(LoginMemberDto(kakaoName,kakaoNum,kakaoName,kakaoName,"?",0,"?","?",4,"",""))
                            System.out.println(msg)
                        }
                        Toast.makeText(this, "${kakaoName}님 환영합니다.", Toast.LENGTH_SHORT).show()
                        val i = Intent(this,WorkActivity::class.java)
                        startActivity(i)
                    }
                }
            }
        }
        // 카카오 로그인
        kakao_login_button.setOnClickListener{
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

        val moveRegi = findViewById<TextView>(R.id.moveRegi)

        moveRegi.setOnClickListener{
            val i = Intent(this, RegiActivity::class.java)
            startActivity(i)
        }

        val moveFindIdPwd = findViewById<TextView>(R.id.moveFIndIdPwd)

        moveFindIdPwd.setOnClickListener{
            val i = Intent(this, FindActivity::class.java)
            startActivity(i)
        }

        sign_in_button.setOnClickListener {
            startActivityForResult(googleSignIntent, RESULT_CODE)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == RESULT_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            result?.let{
                if(it.isSuccess){
                    it.signInAccount?.displayName
                    it.signInAccount?.email
                    Log.e("Value", it.signInAccount?.email!!)
                }else{
                    Log.e("Value", "error")
                }
            }

            if(result!!.isSuccess){
                firebaseLogin(result.signInAccount!!)
            }
        }
    }

    private fun firebaseLogin(googleAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)

        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                it.result?.user?.displayName
                Log.d("@@@@@구글사용자이름",it.result?.user?.displayName!!)
                Log.d("@@@@@구글사용자이메일",it.result?.user?.email!!)

                val idmsg = LoginMemberDao.getInstance().getId_M(LoginMemberDto(it.result?.user?.email!!,"","","","",0,"","",4,"",""))

                if(idmsg=="n"){
//                    val dto = LoginMemberDao.getInstance().login_M(LoginMemberDto(it.result?.user?.email!!, ))
                }

            }else {

            }
        }.addOnFailureListener {

        }
    }
}