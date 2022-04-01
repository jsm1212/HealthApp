package com.example.healthapp.admin

import com.example.healthapp.RetrofitClient
import com.example.healthapp.bbs.WorkBbsService
import com.example.healthapp.login.LoginMemberDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AdminService {
    // 회원 목록
    @POST("/getMem_M")
    fun getMem_M(): Call<List<LoginMemberDto>>

    // 회원 삭제
    @POST("/deleteMem_M")
    fun deleteMem_M(@Body id:String): Call<String>
}
class AdminDao{
    companion object {
        var adminDao: AdminDao? = null

        fun getInstance(): AdminDao {
            if (adminDao == null) {
                adminDao = AdminDao()
            }
            return adminDao!!
        }
    }
    // 회원 목록
    fun getMem_M() : ArrayList<LoginMemberDto>?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(AdminService::class.java)
        val call = service?.getMem_M()
        val response = call?.execute()

        return response?.body() as ArrayList<LoginMemberDto>
    }

    // 회원 삭제
    fun deleteMem_M(id:String): String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(AdminService::class.java)
        val call = service?.deleteMem_M(id)
        val response = call?.execute()

        return response?.body() as String
    }
}