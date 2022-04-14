package com.example.healthapp.bbs

import com.example.healthapp.RetrofitClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WorkBbsService {

    // 게시판 리스트 불러오기
    @GET("/getBbsList")
    fun getBbsList():Call<List<BbsDto>>

    // 게시판 글 작성하기
    @POST("/writebbs_M")
    fun writebbs(@Body dto: BbsDto) : Call<String>

    // 게시판 글 삭제
    @GET("/deleteBbs")
    fun deleteBbs(@Query("seq") seq: Int) : Call<String>

    // 게시판 글 수정
    @POST("/updateBbs_M")
    fun updateBbs(@Body dto: BbsDto) : Call<String>

    // 선택한 게시판 정보 불러오기
    @POST("/bbsDetail_M")
    fun bbsDetail_M(@Body dto: ReadCountBbsDto) : Call<BbsDto>

    // 선택한 게시판 좋아요
    @POST("/likeCount_M")
    fun likeCount_M(@Body dto: LikeBbsDto) : Call<String>

    // 선택한 게시판 좋아요 취소
    @POST("/likeCountCancel_M")
    fun likeCountCancel_M(@Body dto: LikeBbsDto) : Call<String>

    // 검색한 게시판 불러오기
    @POST("/getBbsListSearch_M")
    fun getBbsListSearch_M(@Body dto: BbsParamDto) : Call<List<BbsDto>>
}

class BbsDao {
    companion object{
        var workBbsDao: BbsDao? = null
        var bbsSeq:Int? = null
        var bbsData: BbsDto? = null // 내 댓글 목록에 필요

        fun getInstance(): BbsDao {
            if(workBbsDao == null){
                workBbsDao = BbsDao()
            }
            return workBbsDao!!
        }
    }
    // 게시판 리스트 불러오기
    fun getBbsList() : ArrayList<BbsDto>?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.getBbsList()
        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }

    // 게시판 글 작성하기
    fun writebbs(dto: BbsDto) : String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.writebbs(dto)
        val response = call?.execute()

        return response?.body() as String
    }

    // 게시판 글 삭제하기
    fun deleteBbs(seq: Int) :String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.deleteBbs(seq)
        val response = call?.execute()

        return response?.body() as String
    }

    // 게시판 글 수정하기
    fun updateBbs(dto: BbsDto) : String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.updateBbs(dto)
        val response = call?.execute()

        return response?.body() as String
    }

    // 선택한 게시판 정보 불러오기

    fun bbsDetail_M(dto: ReadCountBbsDto) : BbsDto{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.bbsDetail_M(dto)
        val response = call?.execute()

        return response?.body() as BbsDto
    }

    // 선택한 게시판 좋아요
    fun likeCount_M(dto: LikeBbsDto) : String {
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.likeCount_M(dto)
        val response = call?.execute()

        return response?.body() as String
    }

    // 선택한 게시판 좋아요 취소
    fun likeCountCancel_M(dto: LikeBbsDto) : String {
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.likeCountCancel_M(dto)
        val response = call?.execute()

        return response?.body() as String
    }

    // 검색한 게시판 불러오기
    fun getBbsListSearch_M(dto: BbsParamDto) : ArrayList<BbsDto>{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.getBbsListSearch_M(dto)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }
}