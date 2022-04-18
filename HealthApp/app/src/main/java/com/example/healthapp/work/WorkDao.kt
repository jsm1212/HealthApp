package com.example.healthapp.work

import com.example.healthapp.RetrofitClient
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface WorkListService{
    @GET("/getWorkList")
    fun getWorkList():Call<ArrayList<WorkDto>>

    @GET("/getWorkDetail")
    fun getWorkDetail(@Query("workseq") seq: Int) : Call<WorkDto>

    // 좋아요
    @POST("/likeCountWork_M")
    fun likeCountWork_M(@Body dto:LikeWorkDto) : Call<String>

    // 좋아요 취소
    @POST("/likeCountCancelWork_M")
    fun likeCountCancelWork_M(@Body dto:LikeWorkDto) : Call<String>
}
class WorkDao {
    companion object{
        var workDao:WorkDao?=null
        var workseq:Int? = null

        fun getInstance():WorkDao{
            if(workDao==null)
                workDao= WorkDao()
            return workDao!!
        }
    }
    fun getWorkList():ArrayList<WorkDto>? {
        val retrofit=RetrofitClient.getInstance()
        val service = retrofit?.create(WorkListService::class.java)
        val call = service?.getWorkList()
        val response = call?.execute()
        return response?.body() as ArrayList<WorkDto>
    }

    fun getWorkDetail(seq:Int):WorkDto{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(WorkListService::class.java)
        val call = service?.getWorkDetail(seq)
        val response = call?.execute()
        return response?.body() as WorkDto
    }

    // 좋아요
    fun likeCountWork_M(dto: LikeWorkDto) : String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkListService::class.java)
        val call = service?.likeCountWork_M(dto)
        val response = call?.execute()

        return response?.body() as String
    }

    // 좋아요 취소
    fun likeCountCancelWork_M(dto: LikeWorkDto) : String {
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkListService::class.java)
        val call = service?.likeCountCancelWork_M(dto)
        val response = call?.execute()

        return response?.body() as String
    }
}