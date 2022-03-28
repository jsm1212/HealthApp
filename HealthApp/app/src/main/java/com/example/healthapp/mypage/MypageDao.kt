package com.example.healthapp.mypage

import com.example.healthapp.*
import com.example.healthapp.login.LoginMemberDto
import retrofit2.Call
import retrofit2.http.POST

interface MypageService {
    // 회원정보 수정
    @POST("/updateMember_M")
    fun updateMember_M(dto: LoginMemberDto): Call<String>
}
class MypageDao {
    companion object{
        var mypageDao: MypageDao? = null

        fun getInstance():MypageDao{
            if(mypageDao == null){
                mypageDao = MypageDao()
            }
            return mypageDao!!
        }
    }

    fun updateMember_M(dto: LoginMemberDto): LoginMemberDto?{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.updateMember_M(dto)
        var response = call?.execute()

        return response?.body() as LoginMemberDto
    }
}