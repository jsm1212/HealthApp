package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title = findViewById<EditText>(R.id.titleEditText)
        val content = findViewById<EditText>(R.id.contextEditText)

        val date = findViewById<TextView>(R.id.diaryTextView)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 달력 날짜가 선택되면
            date.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            date.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            title.setText("")
            content.setText("") // EditText에 공백값 넣기

     }


        val saveBtn = findViewById<Button>(R.id.save_Btn)

        saveBtn.setOnClickListener{
            val t = title.text.toString()
            Log.d("@@@@@@",t)
            val c = content.text.toString()
            Log.d("@@@@@@",c)

            val msg = CalendarDao.getInstance().writeCalendar_M(CalendarDto(0,t,c,"",0,"id",date.text.toString()))
            if( msg != null) {
            Log.d("@@@@@에러", msg!!)

            if(msg == "YES") {
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()
            }
            }
        }
    }

}