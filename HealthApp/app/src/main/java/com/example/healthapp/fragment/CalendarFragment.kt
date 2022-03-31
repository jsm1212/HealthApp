package com.example.healthapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.healthapp.CalendarDao
import com.example.healthapp.CalendarDto
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDao


class CalendarFragment(val activity:Context) : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val content = view.findViewById<EditText>(R.id.contextEditText) //내용 Edit
        val date = view.findViewById<TextView>(R.id.diaryTextView) //날짜 TEXT
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView) //달력
        val contentView = view.findViewById<TextView>(R.id.contentView) //내용 TEXT
        val caltextView = view.findViewById<TextView>(R.id.caltextView) //안내 TEXT
        val saveBtn = view.findViewById<Button>(R.id.save_Btn) //저장 버튼
        val chrBtn = view.findViewById<Button>(R.id.cha_Btn) //수정 버튼
        val delBtn = view.findViewById<Button>(R.id.del_Btn) //삭제 버튼

        //감추기,보이기
        contentView.visibility = View.INVISIBLE
        chrBtn.visibility = View.INVISIBLE
        delBtn.visibility = View.INVISIBLE
        caltextView.visibility = View.INVISIBLE
        saveBtn.visibility = View.INVISIBLE
        content.visibility = View.INVISIBLE

        //유저 아이디
        val currentUserId = LoginMemberDao.user?.id
        System.out.println("@@@@@@유저"+currentUserId)

        content.setText("")

        // 달력 날짜 선택
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            date.visibility = View.VISIBLE
            date.text = String.format("%d/%d/%d", year, month + 1, dayOfMonth)
            System.out.println("@@@@@@@@!!!!!"+date.text.toString())
            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.

            content.setText("") // EditText에 공백값 넣기
            val dto = CalendarDao.getInstance().searchCalendar(CalendarDto(0,"","","",0,currentUserId.toString(),date.text.toString()))
                if(dto != null){
                    contentView.text=""
                    contentView.text="내용:${dto.content}"
                    contentView.visibility = View.VISIBLE
                    chrBtn.visibility = View.VISIBLE
                    delBtn.visibility = View.VISIBLE
                    caltextView.visibility = View.VISIBLE
                    saveBtn.visibility = View.INVISIBLE
                    content.visibility = View.VISIBLE
                }else{
                    contentView.text=""
                    caltextView.visibility = View.VISIBLE
                    contentView.visibility = View.VISIBLE
                    saveBtn.visibility = View.VISIBLE
                    chrBtn.visibility = View.INVISIBLE
                    delBtn.visibility = View.INVISIBLE
                    content.visibility = View.VISIBLE
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
                    saveBtn.visibility = View.INVISIBLE
                    content.visibility = View.INVISIBLE
                }else{
                    Toast.makeText(activity, "제거하지 못했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }

        saveBtn.setOnClickListener{
            val c = content.text.toString()
            val msg = CalendarDao.getInstance().writeCalendar_M(CalendarDto(0,"",c,"",0,currentUserId,date.text.toString()))
            if( msg != null) {
                if(msg == "YES") {
                    Toast.makeText(activity, "저장되었습니다.", Toast.LENGTH_LONG).show()
                    contentView.visibility = View.INVISIBLE
                    chrBtn.visibility = View.INVISIBLE
                    delBtn.visibility = View.INVISIBLE
                    caltextView.visibility = View.INVISIBLE
                    saveBtn.visibility = View.INVISIBLE
                    content.visibility = View.INVISIBLE
                }else{
                    Toast.makeText(activity, "저장하지 못했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }

        chrBtn.setOnClickListener {
            val c = content.text.toString()
            caltextView.visibility = View.VISIBLE
            saveBtn.visibility = View.VISIBLE
            val msg = CalendarDao.getInstance().updateCalendar(CalendarDto(0,"",c,"",0,currentUserId,date.text.toString()))
            if( msg != null) {
                if(msg == "OK") {
                    Toast.makeText(activity, "수정되었습니다.", Toast.LENGTH_LONG).show()
                    contentView.visibility = View.INVISIBLE
                    chrBtn.visibility = View.INVISIBLE
                    delBtn.visibility = View.INVISIBLE
                    caltextView.visibility = View.INVISIBLE
                    saveBtn.visibility = View.INVISIBLE
                    content.visibility = View.INVISIBLE
                }
                else{
                    Toast.makeText(activity, "수정하지 못했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
        return view
    }
}


