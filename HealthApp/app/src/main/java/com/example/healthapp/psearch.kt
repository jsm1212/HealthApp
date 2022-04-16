package com.example.healthapp

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.login.LoginMemberDao
import java.text.SimpleDateFormat
import java.util.*

class psearch : AppCompatActivity() {
    lateinit var select:Calendar
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_psearch)

        getSupportActionBar()!!.setIcon(R.drawable.appbar)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setElevation(0F)

        // 오늘날짜 불러오기
        val now = Date(System.currentTimeMillis())
        val nowYear = SimpleDateFormat("yyyy").format(now).toString().toInt()
        val nowMonth = SimpleDateFormat("MM").format(now).toString().toInt()
        val nowDay = SimpleDateFormat("dd").format(now).toString().toInt()
        println("!!!!!!!!!!" + nowYear + nowMonth + nowDay)

        // 시작날짜
        val start = findViewById<ImageView>(R.id.startDate)
        var startView = findViewById<TextView>(R.id.startDateView)
        var startDate = ""
        start.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, R.style.datedialog, {_, year, month, day ->
                if((month+1) < 10){
                    if(day < 10){
                        startView.text = "$year/0${(month+1)}/0$day"
                        startDate = "${year}0${(month+1)}0$day"
                    }else{
                        startView.text = "$year/0${(month+1)}/$day"
                        startDate = "${year}0${(month+1)}$day"
                    }
                }else{
                    if(day < 10){
                        startView.text = "$year/${(month+1)}/0$day"
                        startDate = "${year}${(month+1)}0$day"
                    }else{
                        startView.text = "$year/${(month+1)}/$day"
                        startDate = "${year}${(month+1)}$day"
                    }
                }

                select = Calendar.getInstance().apply { set(year, month, day) }
            }, nowYear, nowMonth-1, nowDay)
            datePickerDialog.show()
        }

        // 종료날짜
        val end = findViewById<ImageView>(R.id.endDate)
        var endView = findViewById<TextView>(R.id.endDateView)
        var endDate = ""

        end.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, R.style.datedialog, {_, year, month, day ->
                if((month+1) < 10){
                    if(day < 10){
                        endView.text = "$year/0${(month+1)}/0$day"
                        endDate = "${year}0${(month+1)}0$day"
                    }else{
                        endView.text = "$year/0${(month+1)}/$day"
                        endDate = "${year}0${(month+1)}$day"
                    }
                }else{
                    if(day < 10){
                        endView.text = "$year/${(month+1)}/0$day"
                        endDate = "${year}${(month+1)}0$day"
                    }else{
                        endView.text = "$year/${(month+1)}/$day"
                        endDate = "${year}${(month+1)}$day"
                    }
                }
            }, nowYear, nowMonth-1, nowDay)

            // 시작날짜 이전은 비활성화
            datePickerDialog.datePicker.minDate = select.timeInMillis
            datePickerDialog.show()
        }

        // 날짜검색
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val dateSearchBtn = findViewById<Button>(R.id.datesearchBtn)
        dateSearchBtn.setOnClickListener {
            println("!!!!!시작 날짜 " + startDate + " 종료날짜 " + endDate)
            val calList = CalendarDao.getInstance().psearchCalendar_M(
                startDate.toInt(), endDate.toInt(), LoginMemberDao.user?.id!!)
            if(calList!!.isEmpty()){
                Toast.makeText(this,"검색 결과가 존재하지 않습니다.",Toast.LENGTH_LONG).show()
            }
            try{
                val adapter = CalendarAdapter(this, calList!!)
                recyclerView.adapter = adapter

                val layout = LinearLayoutManager(this)
                recyclerView.layoutManager = layout
                recyclerView.setHasFixedSize(true)
            }catch(e:Exception){
                Log.d("Log.d", e.toString())
            }
        }
    }
//    private fun choiceDate(year:Int, month:Int, day:Int) : String{
//        var Month = month.toString()
//        var Day = day.toString()
//        if(Month.toInt() in 1..9){
//            Month = "0${month}"
//        }
//        if(Day.toInt() in 1..9){
//            Day = "0${day}"
//        }
//
//        return year.toString() + Month + Day
//    }
}