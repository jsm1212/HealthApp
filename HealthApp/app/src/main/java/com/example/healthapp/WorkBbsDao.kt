package com.example.healthapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WorkBbsService {

    @GET("/getBbsList")
    fun getBbsList():Call<List<WorkBbsDto>>
}

class WorkBbsDao {

    companion object{
        var workBbsDao:WorkBbsDao? = null

        fun getInstance():WorkBbsDao{
            if(workBbsDao == null){
                workBbsDao = WorkBbsDao()
            }
            return workBbsDao!!
        }
    }

    fun getBbsList() : ArrayList<WorkBbsDto>?{
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(WorkBbsService::class.java)
        val call = service?.getBbsList()
        val response = call?.execute()

        return response?.body() as ArrayList<WorkBbsDto>
    }
}