package com.example.healthapp.bbs

import com.example.healthapp.RetrofitClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BbsReplyService{

    // 댓글 리스트 불러오기
    @GET("/getReplyList_M")
    fun getReplyList(@Query("seq") seq:Int) : Call<ArrayList<BbsReplyDto>>

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

}