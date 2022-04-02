package com.example.healthapp.admin

import com.example.healthapp.RetrofitClient
import com.example.healthapp.login.LoginMemberDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AdminService {
    // 회원 목록
    @GET("/getMem_M")
    fun getMem_M(): Call<List<LoginMemberDto>>


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
}