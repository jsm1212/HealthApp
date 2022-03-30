package com.example.healthapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.example.healthapp.CalendarDao
import com.example.healthapp.CalendarDto
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDao
import java.util.*

class CalendarFragment(val activity:Context) : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val content = view.findViewById<EditText>(R.id.contextEditText)
        val date = view.findViewById<TextView>(R.id.diaryTextView)
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val contentView = view.findViewById<TextView>(R.id.contentView)
        val caltextView = view.findViewById<TextView>(R.id.caltextView)
        val contextEditText = view.findViewById<EditText>(R.id.contextEditText)
        val saveBtn = view.findViewById<Button>(R.id.save_Btn)
        val chrBtn = view.findViewById<Button>(R.id.cha_Btn)
        val delBtn = view.findViewById<Button>(R.id.del_Btn)
        contentView.visibility = View.VISIBLE
        chrBtn.visibility = View.VISIBLE
        delBtn.visibility = View.VISIBLE
        caltextView.visibility = View.VISIBLE
        contextEditText.visibility = View.VISIBLE
        saveBtn.visibility = View.VISIBLE

        val currentUserId = LoginMemberDao.user?.id

        content.setText("")
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 달력 날짜가 선택되면
            date.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            date.text = String.format("%d/%d/%d", year, month + 1, dayOfMonth)
            System.out.println("@@@@@@@@!!!!!"+date.text.toString())
            // var selectDate = "${year}/${month+1}/${dayOfMonth}"
            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            //title.setText("")
            content.setText("") // EditText에 공백값 넣기
            val dto = CalendarDao.getInstance().searchCalendar(CalendarDto(0,"","","",0,currentUserId.toString(),date.text.toString()))
            if(dto != null){
                contentView.text=""
                contentView.text="내용:${dto.content}"
                contentView.visibility = View.VISIBLE
                chrBtn.visibility = View.VISIBLE
                delBtn.visibility = View.VISIBLE
                caltextView.visibility = View.VISIBLE
                contextEditText.visibility = View.VISIBLE
                saveBtn.visibility = View.INVISIBLE
            }else{
                contentView.text=""
                caltextView.visibility = View.VISIBLE
                contextEditText.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                chrBtn.visibility = View.INVISIBLE
                delBtn.visibility = View.INVISIBLE
            }
     }

        delBtn.setOnClickListener {
            val msg = CalendarDao.getInstance().deleteCalendar(CalendarDto(0,"","","",0,currentUserId,date.text.toString()))
            if( msg != null) {
                if(msg == "OK") {
                    Toast.makeText(activity, "삭제되었습니다.", Toast.LENGTH_LONG).show()
                    contentView.visibility = View.INVISIBLE
                    chrBtn.visibility = View.INVISIBLE
                    delBtn.visibility = View.INVISIBLE
                    caltextView.visibility = View.INVISIBLE
                    contextEditText.visibility = View.INVISIBLE
                    saveBtn.visibility = View.INVISIBLE
                }else{
                    Log.e("@@@@@삭제에러",msg)
                }
            }
        }

        saveBtn.setOnClickListener{
            Log.d("@@@@@@", "aaaaaaaaaa!!!!!!!!!!!!!")
           // val t = title.text.toString()
            //Log.d("@@@@@@",t)
            val c = content.text.toString()
            Log.d("@@@@@@",c)
            val msg = CalendarDao.getInstance().writeCalendar_M(CalendarDto(0,"",c,"",0,currentUserId,date.text.toString()))
            if( msg != null) {
                Log.d("@@@@@에러", msg!!)
                if(msg == "YES") {
                    Toast.makeText(activity, "저장되었습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
        chrBtn.setOnClickListener {
            val c = content.text.toString()
            caltextView.visibility = View.VISIBLE
            contextEditText.visibility = View.VISIBLE
            saveBtn.visibility = View.VISIBLE
            val msg = CalendarDao.getInstance().updateCalendar(CalendarDto(0,"",c,"",0,currentUserId,date.text.toString()))
            if( msg != null) {
                if(msg == "OK") {
                    Toast.makeText(activity, "수정되었습니다.", Toast.LENGTH_LONG).show()
                    contentView.visibility = View.INVISIBLE
                    chrBtn.visibility = View.INVISIBLE
                    delBtn.visibility = View.INVISIBLE
                    caltextView.visibility = View.INVISIBLE
                    contextEditText.visibility = View.INVISIBLE
                    saveBtn.visibility = View.INVISIBLE
                }
            }
        }
        return view

    }

}


