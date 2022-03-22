package com.example.healthapp.workbbs

import com.example.healthapp.RetrofitClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WorkBbsService {

    // 게시판 리스트 불러오기
    @GET("/getBbsList")
    fun getBbsList():Call<List<WorkBbsDto>>

    // 게시판 글 작성하기
    @POST("/writebbs_M")
    fun writebbs(@Body dto: WorkBbsDto) : Call<String>
}

class WorkBbsDao {

    companion object{
        var workBbsDao: WorkBbsDao? = null

        fun getInstance(): WorkBbsDao {
            if(workBbsDao == null){
                workBbsDao = WorkBbsDao()
            }
            return workBbsDao!!
        }
    }
    // 게시판 리스트 불러오기
    fun getBbsList() : ArrayList<WorkBbsDto>?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.getBbsList()
        val response = call?.execute()

        return response?.body() as ArrayList<WorkBbsDto>
    }

    // 게시판 글 작성하기
    fun writebbs(dto: WorkBbsDto) : String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.writebbs(dto)
        val response = call?.execute()

        return response?.body() as String
    }
}