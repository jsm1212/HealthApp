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

    @GET("/deleteBbs")
    fun deleteBbs(@Query("seq") seq: Int) : Call<String>
}

class BbsDao {

    companion object{
        var workBbsDao: BbsDao? = null

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
}