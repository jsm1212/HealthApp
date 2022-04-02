package com.example.healthapp.mypage

import com.example.healthapp.*
import com.example.healthapp.admin.AdminService
import com.example.healthapp.bbs.*
import com.example.healthapp.login.LoginMemberDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MypageService {
    // 회원정보 호출
    @POST("/searchMember_M")
    fun searchMember_M(@Body id:String): Call<LoginMemberDto>

    // 회원정보 수정
    @POST("/updateMember_M")
    fun updateMember_M(@Body dto:LoginMemberDto): Call<String>

    // 회원 삭제
    @POST("/deleteMem_M")
    fun deleteMem_M(@Body id:String): Call<String>

    // 운동 루틴
//    @POST("/getMyRoutine_M")
//    fun getMyRoutine_M(@Body nick:String): Call<String>

    // 내 게시글 목록
    @POST("/getMyBbs_M")
    fun getMyBbs_M(@Body id:String): Call<List<BbsDto>>

    // 내 댓글 목록
    @POST("/getMyReply_M")
    fun getMyReply_M(@Body id:String): Call<List<BbsReplyDto>>

    // 좋아요 누른 글 목록
    @POST("/getMyLike_M")
    fun getMyLike_M(@Body id:String): Call<List<BbsDto>>
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
    // 회원정보 호출
    fun searchMember_M(id:String) : LoginMemberDto{
        println("확인!!!!!!!!!! 회원정보 호출!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.searchMember_M(id)
        val response = call?.execute()

        return response?.body() as LoginMemberDto
    }

    // 회원정보 수정
    fun updateMember_M(dto:LoginMemberDto): String{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.updateMember_M(dto)
        var response = call?.execute()

        return response?.body() as String
    }

    // 회원 삭제
    fun deleteMem_M(id:String): String{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.deleteMem_M(id)
        val response = call?.execute()

        return response?.body() as String
    }

    // 운동 루틴
//    fun getMyRoutine_M(id:String) : ArrayList<BbsReplyDto>?{
//        val retrofit = RetrofitClient.getInstance()
//        val service = retrofit?.create(MypageService::class.java)
//        val call = service?.getMyBbs_M(id)
//        val response = call?.execute()
//
//        return response?.body() as ArrayList<BbsReplyDto>
//    }

    // 내 게시글 목록
    fun getMyBbs_M(id:String) : ArrayList<BbsDto>?{
        println("확인!!!!!!!!!! 게시글 목록!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getMyBbs_M(id)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }

    // 내 댓글 목록
    fun getMyReply_M(id:String) : ArrayList<BbsReplyDto>?{
        println("확인!!!!!!!!!! 댓글 목록!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getMyReply_M(id)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsReplyDto>
    }

    // 좋아요 누른 글 목록
    fun getMyLike_M(id:String) : ArrayList<BbsDto>?{
        println("확인!!!!!!!!!! 좋아요 목록!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getMyLike_M(id)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }
}