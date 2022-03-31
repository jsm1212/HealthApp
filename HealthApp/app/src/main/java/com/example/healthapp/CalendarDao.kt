package com.example.healthapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface CalendarService{

    @POST("/writeCalendar_M")
    fun writeCalendar_M(@Body dto: CalendarDto): Call<String>

    @POST("/updateCalendar")
    fun updateCalendar(@Body dto: CalendarDto): Call<String>

    @POST("/searchCalendar")
    fun searchCalendar(@Body dto:CalendarDto): Call<CalendarDto>

    @POST("/deleteCalendar")
    fun deleteCalendar(@Body dto:CalendarDto): Call<String>
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
            System.out.println("@@@@@@@@"+e.toString())
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
}
