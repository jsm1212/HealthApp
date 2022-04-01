package com.example.healthapp.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.healthapp.R
import com.example.healthapp.admin.AdminActivity
import com.example.healthapp.bbs.WorkActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
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

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        val loginEditId = findViewById<EditText>(R.id.loginEditId)
        val loginEditPwd = findViewById<EditText>(R.id.loginEditPwd)

        val loginBtn = findViewById<Button>(R.id.loginBtn)

        // 자동로그인
        val auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE)
        val autoLoginUserId = auto.getString("userId", null)
        val autoLoginUserPwd = auto.getString("userPwd", null)

        if(autoLoginUserId != null && autoLoginUserPwd != null){
            val dto = LoginMemberDao.getInstance().login_M(LoginMemberDto(autoLoginUserId,autoLoginUserPwd,"","","",0,"","",3,"","", 0))

            if(dto != null){
                LoginMemberDao.user=dto

                Toast.makeText(this, "${dto.nickname}님 환영합니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, WorkActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(this, "자동로그인 정보 호출에 실패했습니다.", Toast.LENGTH_LONG).show()
            }
        }

        // 일반회원 로그인
        loginBtn.setOnClickListener {
            val id = loginEditId.text.toString().trim()
            val pwd = loginEditPwd.text.toString().trim()
            val autoLogin = findViewById<CheckBox>(R.id.autoLogin)

            val dto = LoginMemberDao.getInstance().login_M(LoginMemberDto(id, pwd, "", "", "", 0, "", "", 0, "","", 0))

            // 자동로그인이 체크되어있으면 앱 파일에 로그인 데이터 저장(앱 꺼져도 유지)
            if(autoLogin.isChecked){
                val auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE)
                val autoLoginEdit = auto.edit()

                autoLoginEdit.putString("userId", id)
                autoLoginEdit.putString("userPwd", pwd)
                autoLoginEdit.commit()
            }

            if (dto != null) {
                if(dto.auth == 3){
                    LoginMemberDao.user = dto

                    Toast.makeText(this, "${dto.nickname}님 환영합니다", Toast.LENGTH_LONG).show()

                    val i = Intent(this, WorkActivity::class.java)
                    startActivity(i)
                } else if(dto.auth == 1){
                    LoginMemberDao.user = dto

                    Toast.makeText(this, "관리자 로그인", Toast.LENGTH_LONG).show()

                    val a = Intent(this, AdminActivity::class.java)
                    startActivity(a)
                }
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
                        Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show()
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
                        Log.i(TAG, "사용자 이메일${user.kakaoAccount!!.email}")

                        val kakaoName = user.kakaoAccount!!.profile!!.nickname
                        val kakaoNum = user.id.toString()
                        val email = user.kakaoAccount!!.email

                        val checkId = LoginMemberDao.getInstance().getId_M(LoginMemberDto(kakaoNum,"","","","",0,"","",0,"","", 0))
                        if(checkId!="n"){
                            val kakaoRegi = LoginMemberDao.getInstance().register_M(LoginMemberDto(kakaoNum,kakaoNum,kakaoName,kakaoName," ",0,email," ",4,"","", 0))
                            if(kakaoRegi == "y"){
                                val kakaoLogin = LoginMemberDao.getInstance().login_M(LoginMemberDto(kakaoNum,kakaoNum,kakaoName,kakaoName," ",0,email," ",4,"","", 0))
                                LoginMemberDao.user = kakaoLogin
                                Toast.makeText(this, "${kakaoName}님 환영합니다.", Toast.LENGTH_SHORT).show()
                                val i = Intent(this,WorkActivity::class.java)
                                startActivity(i)
                            }else{
                                Toast.makeText(this, "카카오 로그인 에러", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            val kakaoLogin = LoginMemberDao.getInstance().login_M(LoginMemberDto(kakaoNum,kakaoNum,kakaoName,kakaoName," ",0,email," ",4,"","", 0))
                            if(kakaoLogin != null){
                                LoginMemberDao.user = kakaoLogin
                                Toast.makeText(this, "${kakaoName}님 환영합니다.", Toast.LENGTH_SHORT).show()
                                val i = Intent(this,WorkActivity::class.java)
                                startActivity(i)
                            }else{
                                Toast.makeText(this, "카카오 로그인 에러", Toast.LENGTH_SHORT).show()
                            }
                        }
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

        // 회원가입으로 이동
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

        if(resultCode == RESULT_OK && requestCode == RESULT_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            result?.let{
                if(it.isSuccess){
                    val googleUserName = it.signInAccount?.displayName
                    val googleUserEmail = it.signInAccount?.email
                    val googleUserIdtoken = it.signInAccount?.id
                    val googleUserNickname = it.signInAccount?.givenName

                    Log.i("email", it.signInAccount?.email!!) //이메일
                    Log.i("name", it.signInAccount?.displayName!!) //이름
                    Log.i("idtoken", it.signInAccount?.id!!) // 토큰
                    Log.i("nickname", it.signInAccount?.givenName!!) //닉네임

                    val checkId = LoginMemberDao.getInstance().getId_M(LoginMemberDto(googleUserIdtoken,"","","","",0,"","",0,"","", 0))
                    if(checkId != "n"){
                        val googleRegi = LoginMemberDao.getInstance().register_M(LoginMemberDto(googleUserIdtoken,googleUserIdtoken,googleUserName,googleUserNickname," ",0,googleUserEmail," ",5,"","", 0))
                        if(googleRegi == "y"){
                            val googleLogin = LoginMemberDao.getInstance().login_M(LoginMemberDto(googleUserIdtoken,googleUserIdtoken,googleUserName,googleUserNickname," ",0,googleUserEmail," ",5,"","", 0))
                            LoginMemberDao.user = googleLogin
                            Toast.makeText(this,"${googleUserNickname}님 환영합니다.",Toast.LENGTH_LONG).show()
                            val i = Intent(this,WorkActivity::class.java)
                            startActivity(i)
                        }else{
                            Toast.makeText(this,"구글 로그인 에러",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        val googleLogin = LoginMemberDao.getInstance().login_M(LoginMemberDto(googleUserIdtoken,googleUserIdtoken,"","","",0,"","",0,"","", 0))
                        if(googleLogin != null){
                            LoginMemberDao.user = googleLogin
                            Toast.makeText(this,"${googleUserNickname}님 환영합니다.",Toast.LENGTH_LONG).show()
                            val i = Intent(this,WorkActivity::class.java)
                            startActivity(i)
                        }else{
                            Toast.makeText(this,"구글 로그인 에러",Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    Log.e("Value", "error")
                }
            }

            if(result!!.isSuccess){
                firebaseLogin(result.signInAccount!!)
            }
        }
    }

    // 구글계정받아서 파이어베이스 로그인
    private fun firebaseLogin(googleAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)

        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                it.result?.user?.displayName
                Log.i("@@@@@파이어베이스사용자이름",it.result?.user?.displayName!!)
                Log.i("@@@@@파이어베이스사용자이메일",it.result?.user?.email!!)

            }else {

            }
        }.addOnFailureListener {

        }



    }
}