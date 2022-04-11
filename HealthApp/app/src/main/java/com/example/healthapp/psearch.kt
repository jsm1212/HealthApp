package com.example.healthapp


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.login.LoginMemberDao
import java.time.LocalDate

class psearch : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psearch)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val sDatePicker = findViewById<DatePicker>(R.id.SdatePicker)
        val eDatePicker = findViewById<DatePicker>(R.id.EdatePicker)
        val dateSearchBtn = findViewById<Button>(R.id.datesearchBtn)
        val calmenuBtn = findViewById<Button>(R.id.calmenuBtn)
        val nowDate = LocalDate.now().toString()
        var nowDateStr:String = nowDate.replace("-", "")

        // 시작날짜
        var startDate = nowDateStr
        // 종료날짜
        var endDate = nowDateStr

        sDatePicker.setOnDateChangedListener{ _, year, month, date ->
            startDate = choiceDate(year, (month+1), date)
        }
        eDatePicker.setOnDateChangedListener{ _, year, month, date ->
            endDate = choiceDate(year, (month+1), date)
        }
        calmenuBtn.setOnClickListener {
            super.onBackPressed()
        }

        dateSearchBtn.setOnClickListener {
            val calList = CalendarDao.getInstance().psearchCalendar_M(
                startDate.toInt(), endDate.toInt(), LoginMemberDao.user?.id!!)
            if(calList!!.isEmpty()){
                Toast.makeText(this,"입력하신 내역이 존재하지 않습니다.",Toast.LENGTH_LONG).show()
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

    private fun choiceDate(year:Int, month:Int, day:Int) : String{
        var Month = month.toString()
        var Day = day.toString()
        if(Month.toInt() in 1..9){
            Month = "0${month}"
        }
        if(Day.toInt() in 1..9){
            Day = "0${day}"
        }

        return year.toString() + Month + Day
    }

}