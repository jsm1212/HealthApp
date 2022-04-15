package com.example.healthapp.bbs

import com.example.healthapp.RetrofitClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BbsReplyService{

    // 댓글 리스트 불러오기
    @GET("/getReplyList")
    fun getReplyList(@Query("seq") seq:Int) : Call<ArrayList<BbsReplyDto>>

    // 댓글 작성하기
    @POST("/writeReply_M")
    fun writeReply(@Body dto: BbsReplyDto) : Call<String>

    // 댓글 삭제하기
    @GET("/deleteReply")
    fun deleteReply(@Query("seq") seq: Int) : Call<String>

    // 해당 게시글의 댓글 수
    @GET("/getReplyCount")
    fun getReplyCount(@Query("seq") seq: Int) : Call<Int>
}

class BbsReplyDao {

    companion object{
        var BbsReplyDao: BbsReplyDao? = null

        fun getInstance(): BbsReplyDao {
            if(BbsReplyDao == null){
                BbsReplyDao = BbsReplyDao()
            }
            return BbsReplyDao!!
        }
    }

    // 댓글 리스트 불러오기
    fun getReplyList(seq: Int) :ArrayList<BbsReplyDto>?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsReplyService::class.java)
        val call = service?.getReplyList(seq)
        val response = call?.execute()

        return response?.body() as ArrayList<BbsReplyDto>
    }

    // 댓글 작성하기
    fun writeReply(dto: BbsReplyDto) : String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsReplyService::class.java)
        val call = service?.writeReply(dto)
        val response = call?.execute()

        return response?.body() as String
    }

    // 댓글 삭제하기
    fun deleteReply(seq: Int) : String{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsReplyService::class.java)
        val call = service?.deleteReply(seq)
        val response = call?.execute()

        return response?.body() as String
    }

    // 해당 게시글의 댓글 수
    fun getReplyCount(seq: Int) : Int{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsReplyService::class.java)
        val call = service?.getReplyCount(seq)
        val response = call?.execute()

        return response?.body() as Int
    }
}