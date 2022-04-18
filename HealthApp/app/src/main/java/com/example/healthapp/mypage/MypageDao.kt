package com.example.healthapp.mypage

import com.example.healthapp.*
import com.example.healthapp.bbs.*
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.work.LikeWorkDto
import com.example.healthapp.work.WorkDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MypageService {
    // 회원정보 호출
    @POST("/getInformation_M")
    fun getInformation(@Body id:String): Call<LoginMemberDto>

    // 회원정보 수정
    @POST("/updateMember_M")
    fun updateMember(@Body dto:LoginMemberDto): Call<String>

    // 비밀번호 수정
    @POST("/updatePwd_M")
    fun updatePwd(@Body dto:LoginMemberDto): Call<String>

    // 회원 삭제
    @POST("/deleteMember_M")
    fun deleteMember(@Body id:String): Call<String>

    // 운동 루틴
    @POST("/getMyRoutine_M")
    fun getMyRoutine(@Body id:String): Call<List<WorkDto>>

    // 꼼수
    @GET("/getWorkLikeCount")
    fun getWorkLikeCount(): Call<List<LikeWorkDto>>

    // 내 게시글 목록
    @POST("/getMyBbs_M")
    fun getMyBbs(@Body id:String): Call<List<BbsDto>>

    // 내 댓글 목록
    @POST("/getMyReply_M")
    fun getMyReply(@Body id:String): Call<List<BbsReplyDto>>

    // 좋아요 누른 글 목록
    @POST("/getMyLike_M")
    fun getMyLike(@Body id:String): Call<List<BbsDto>>

    // 회원 목록 조회(관리자)
    @GET("/getMemberList")
    fun getMemberList(): Call<List<LoginMemberDto>>

    // 메일 전송
    @POST("/sendEmail_M")
    fun sendEmail(@Body email:String): Call<Int>
}
class MypageDao {
    companion object{
        var mypageDao: MypageDao? = null

        fun getInstance(): MypageDao {
            if(mypageDao == null){
                mypageDao = MypageDao()
            }
            return mypageDao!!
        }
    }
    // 회원정보 호출
    fun getInformation(id:String) : LoginMemberDto{
        println("확인!!!!!!!!!! 회원정보 호출!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getInformation(id)
        val response = call?.execute()

        return response?.body() as LoginMemberDto
    }

    // 회원정보 수정
    fun updateMember(dto:LoginMemberDto): String{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.updateMember(dto)
        var response = call?.execute()

        return response?.body() as String
    }

    // 비밀번호 수정
    fun updatePwd(dto:LoginMemberDto): String{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.updatePwd(dto)
        var response = call?.execute()

        return response?.body() as String
    }

    // 회원 삭제
    fun deleteMember(id:String): String{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.deleteMember(id)
        val response = call?.execute()

        return response?.body() as String
    }

    // 운동 루틴
    fun getWorkLikeCount() : ArrayList<LikeWorkDto>?{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getWorkLikeCount()
        val response = call?.execute()

        return response?.body() as ArrayList<LikeWorkDto>
    }

    // 내 게시글 목록
    fun getMyBbs(id:String) : ArrayList<BbsDto>?{
        println("확인!!!!!!!!!! 게시글 목록!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getMyBbs(id)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }

    // 내 댓글 목록
    fun getMyReply(id:String) : ArrayList<BbsReplyDto>?{
        println("확인!!!!!!!!!! 댓글 목록!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getMyReply(id)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsReplyDto>
    }

    // 좋아요 누른 글 목록
    fun getMyLike(id:String) : ArrayList<BbsDto>?{
        println("확인!!!!!!!!!! 좋아요 목록!!!!!!!!!!")

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getMyLike(id)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }

    // 회원 목록 조회(관리자)
    fun getMemberList() : ArrayList<LoginMemberDto>?{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.getMemberList()
        val response = call?.execute()

        return response?.body() as ArrayList<LoginMemberDto>
    }

    // 메일 전송
    fun sendEmail(email:String) : Int?{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(MypageService::class.java)
        val call = service?.sendEmail(email)
        val response = call?.execute()

        return response?.body() as Int
    }
}