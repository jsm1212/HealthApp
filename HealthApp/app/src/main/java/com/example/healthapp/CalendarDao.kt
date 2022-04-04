package com.example.healthapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface CalendarService{

    @POST("/writeCalendar_M")
    fun writeCalendar_M(@Body dto: CalendarDto): Call<String>

    @POST("/updateCalendar")
    fun updateCalendar(@Body dto: CalendarDto): Call<String>

    @POST("/searchCalendar")
    fun searchCalendar(@Body dto:CalendarDto): Call<CalendarDto>

    @POST("/deleteCalendar")
    fun deleteCalendar(@Body dto:CalendarDto): Call<String>

    @GET("/psearchCalendar")
    fun psearchCalendar(@Query("startDate") startDate: Int, @Query("endDate") endDate: Int,
                        @Query("userId") userId: String): Call<List<CalendarDto>>
}

class CalendarDao {

    companion object {
        var calendarDao: CalendarDao? = null
        fun getInstance(): CalendarDao {
            if (calendarDao == null) {
                calendarDao = CalendarDao()
            }
            return calendarDao!!
        }
    }

    fun writeCalendar_M(dto: CalendarDto): String? {
            val retrofit = RetrofitClient.getInstance()
            val service = retrofit?.create(CalendarService::class.java)
            val call = service?.writeCalendar_M(dto)
            val response = call?.execute()
        return response?.body() as String
    }

    fun updateCalendar(dto: CalendarDto): String?{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(CalendarService::class.java)
        val call = service?.updateCalendar(dto)
        val response = call?.execute()
        return response?.body() as String
    }

    fun searchCalendar(dto: CalendarDto): CalendarDto?{
        var response: Response<CalendarDto>?=null
        try {
            val retrofit = RetrofitClient.getInstance()
            val service = retrofit?.create(CalendarService::class.java)
            val call = service?.searchCalendar(dto)
            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response==null) return null
        return response?.body() as CalendarDto
    }

    fun deleteCalendar(dto: CalendarDto): String?{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(CalendarService::class.java)
        val call = service?.deleteCalendar(dto)
        val response = call?.execute()
        return response?.body() as String
    }

    fun psearchCalendar(startDate: Int, endDate: Int, userId: String) : ArrayList<CalendarDto>?{
//        val retrofit = RetrofitClient.getInstance()
//        val service = retrofit?.create(CalendarService::class.java)
//        val call = service?.psearchCalendar(startDate, endDate, userId)
//        val response = call?.execute()

        var response: Response<List<CalendarDto>>? = null

        try {
            val retrofit = RetrofitClient.getInstance()
            val service = retrofit?.create(CalendarService::class.java)
            val call = service?.psearchCalendar(startDate, endDate, userId)
            response = call?.execute()
            println("try 부분 실행")
        }catch (e:Exception){
            response=null
            println("catch부분 실행")
        }

        return response?.body() as ArrayList<CalendarDto>
    }
}
