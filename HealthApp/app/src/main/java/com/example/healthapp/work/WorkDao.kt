package com.example.healthapp.work

import com.example.healthapp.RetrofitClient
import com.example.healthapp.bbs.BbsDao
import com.example.healthapp.bbs.BbsDto
import com.example.healthapp.bbs.WorkBbsService
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface WorkListService{
    @GET("/getWorkList")
    fun getWorkList():Call<ArrayList<WorkDto>>

    @GET("/getWorkDetail")
    fun getWorkDetail(@Query("workseq") seq: Int) : Call<WorkDto>

    // 선택한 게시판 좋아요
    @GET("/workLikeCount")
    fun workLikeCount(@Query("workseq") seq: Int) : Call<Unit>

    @GET("/workLikeCountCancel")
    fun workLikeCountCancel(@Query("seq") seq: Int) : Call<Unit>

    @GET("/getmylist")
    fun getMyList()

}
class WorkDao {
    companion object{
        var workDao:WorkDao?=null
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

    fun workLikeCount(seq:Int):Unit{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkListService::class.java)
        val call = service?.workLikeCount(seq)
        val response = call?.execute()

        return response?.body() as Unit
    }
    fun workLikeCountCancel(seq:Int):Unit{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkListService::class.java)
        val call = service?.workLikeCount(seq)
        val response = call?.execute()

        return response?.body() as Unit
    }


}