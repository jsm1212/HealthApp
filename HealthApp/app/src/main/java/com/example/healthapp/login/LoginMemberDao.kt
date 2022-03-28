package com.example.healthapp.login

import com.example.healthapp.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginMemberService{
    // 아이디 중복 확인
    @POST("/getId_M")
    fun getId_M(@Body dto: LoginMemberDto): Call<String>

    // 로그인
    @POST("/login_M")
    fun login_M(@Body dto: LoginMemberDto): Call<LoginMemberDto>

    // 회원가입
    @POST("/register_M")
    fun register_M(@Body dto: LoginMemberDto): Call<String>

    // 이메일 중복 확인
    @POST("/checkEmail_M")
    fun checkEmail_M(@Body dto: LoginMemberDto): Call<String>

    // 닉네임 중복 확인
    @POST("/checkNickname_M")
    fun checkNickname_M(@Body dto: LoginMemberDto): Call<String>

    // 회원 전체 조회
    @GET("/allMember")
    fun allMember(): Call<List<LoginMemberDto>>

    // 아이디 찾기
    @POST("/findId_M")
    fun findId_M(@Body dto: LoginMemberDto): Call<LoginMemberDto>

    // 비밀번호 찾기
    @POST("/findPwd_M")
    fun findPwd_M(@Body dto: LoginMemberDto): Call<LoginMemberDto>
}
class LoginMemberDao {

    companion object{
        var loginMemberDao: LoginMemberDao? = null
        var user: LoginMemberDto? = null

        fun getInstance(): LoginMemberDao {
            if(loginMemberDao == null){
                loginMemberDao = LoginMemberDao()
            }
            return loginMemberDao!!
        }
    }

    fun getId_M(dto: LoginMemberDto): String?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(LoginMemberService::class.java)

        val call = service?.getId_M(dto)

        val response = call?.execute()

        return response?.body() as String
    }

    fun login_M(dto: LoginMemberDto): LoginMemberDto?{

        var response: Response<LoginMemberDto>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(LoginMemberService::class.java)

            val call = service?.login_M(dto)

            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as LoginMemberDto
    }

    fun register_M(dto: LoginMemberDto): String?{

        var response: Response<String>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(LoginMemberService::class.java)

            val call = service?.register_M(dto)

            response = call?.execute()
        }catch (e:Exception){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~!!!!"+e.toString())
            response = null
        }
        if(response == null) return null

        return response?.body() as String
    }

    fun checkEmail_M(dto: LoginMemberDto): String?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(LoginMemberService::class.java)

        val call = service?.checkEmail_M(dto)

        val response = call?.execute()

        return response?.body() as String
    }

    fun allMember(): List<LoginMemberDto>?{
        var response: Response<List<LoginMemberDto>>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(LoginMemberService::class.java)

            val call = service?.allMember()

            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as List<LoginMemberDto>
    }

    fun checkNickname_M(dto: LoginMemberDto): String?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(LoginMemberService::class.java)

        val call = service?.checkNickname_M(dto)

        val response = call?.execute()

        return response?.body() as String
    }

    fun findId_M(dto: LoginMemberDto): LoginMemberDto?{
        var response: Response<LoginMemberDto>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(LoginMemberService::class.java)

            val call = service?.findId_M(dto)

            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as LoginMemberDto
    }

    fun findPwd_M(dto: LoginMemberDto): LoginMemberDto?{
        var response: Response<LoginMemberDto>? = null

        try {
            val retrofit = RetrofitClient.getInstance()

            val service = retrofit?.create(LoginMemberService::class.java)

            val call = service?.findPwd_M(dto)

            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as LoginMemberDto
    }


}