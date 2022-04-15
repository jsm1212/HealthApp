package com.example.healthapp.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.healthapp.*
import com.example.healthapp.login.LoginMemberDao


class CalendarFragment(val activity:Context) : Fragment() {

    var dateDB: String? = ""

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        // 뒤로가기 버튼 2번 클릭시 어플 종료
        view.isFocusableInTouchMode=true
        view.requestFocus()
        view.setOnKeyListener(OnMyKeyDown(activity as Activity))

        val content = view.findViewById<EditText>(R.id.contextEditText) //내용 Edit
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView) //달력
        val contentView = view.findViewById<TextView>(R.id.contentView) //내용 TEXT
        val saveBtn = view.findViewById<Button>(R.id.save_Btn) //저장 버튼
        val chrBtn = view.findViewById<Button>(R.id.cha_Btn) //수정 버튼
        val delBtn = view.findViewById<Button>(R.id.del_Btn) //삭제 버튼

        //감추기,보이기
        contentView.visibility = View.INVISIBLE
        chrBtn.visibility = View.INVISIBLE
        delBtn.visibility = View.INVISIBLE
        saveBtn.visibility = View.INVISIBLE
        content.visibility = View.INVISIBLE

        //유저 아이디
        val currentUserId = LoginMemberDao.user?.id

        content.setText("")
        val psearchBtn = view.findViewById<ImageView>(R.id.psearchBtn)
        psearchBtn.setOnClickListener {
            val s = Intent(activity,psearch::class.java)
            startActivity(s)
        }

        // 달력 날짜 선택
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dateDB = choiceDate(year, month+1, dayOfMonth)
            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.

            content.setText("") // EditText에 공백값 넣기
            val dto = CalendarDao.getInstance().searchCalendar_M(CalendarDto(0,"","","",0,currentUserId.toString(),dateDB))
                if(dto != null){
                    contentView.text=""
                    contentView.text="${dto.content}"
                    contentView.visibility = View.VISIBLE
                    chrBtn.visibility = View.VISIBLE
                    delBtn.visibility = View.VISIBLE
                    saveBtn.visibility = View.INVISIBLE
                    content.visibility = View.VISIBLE
                }else{
                    contentView.text=""
                    contentView.visibility = View.VISIBLE
                    saveBtn.visibility = View.VISIBLE
                    chrBtn.visibility = View.INVISIBLE
                    delBtn.visibility = View.INVISIBLE
                    content.visibility = View.VISIBLE
                }
        }

        delBtn.setOnClickListener {
            val msg = CalendarDao.getInstance().deleteCalendar_M(CalendarDto(0,"","","",0,currentUserId,dateDB))
            if( msg != null) {
                if(msg == "OK") {
                    Toast.makeText(activity, "삭제되었습니다.", Toast.LENGTH_LONG).show()
                    contentView.visibility = View.INVISIBLE
                    chrBtn.visibility = View.INVISIBLE
                    delBtn.visibility = View.INVISIBLE
                    saveBtn.visibility = View.INVISIBLE
                    content.visibility = View.INVISIBLE
                }else{
                    Toast.makeText(activity, "제거하지 못했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }

        saveBtn.setOnClickListener{
            val c = content.text.toString()
            if(c != "" && c != null){
                val msg = CalendarDao.getInstance().writeCalendar_M(CalendarDto(0,"",c,"",0,currentUserId,dateDB))
                if(msg != null && msg == "YES") {
                    Toast.makeText(activity, "저장되었습니다.", Toast.LENGTH_LONG).show()
                    contentView.text="${c}"
                    contentView.visibility = View.VISIBLE
                    chrBtn.visibility = View.VISIBLE
                    delBtn.visibility = View.VISIBLE
                    saveBtn.visibility = View.INVISIBLE
                    content.visibility = View.VISIBLE

                }else{
                    Toast.makeText(activity, "저장하지 못했습니다.", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(activity, "내용을 기입해 주세요.", Toast.LENGTH_LONG).show()
            }
        }

        chrBtn.setOnClickListener {
            val c = content.text.toString()
            saveBtn.visibility = View.VISIBLE
                if(c != "" && c != null){
                    val msg = CalendarDao.getInstance().updateCalendar_M(CalendarDto(0,"",c,"",0,currentUserId,dateDB))
                    if( msg != null && msg == "OK") {
                            Toast.makeText(activity, "수정되었습니다.", Toast.LENGTH_LONG).show()
                            contentView.text="${c}"
                            contentView.visibility = View.VISIBLE
                            chrBtn.visibility = View.VISIBLE
                            delBtn.visibility = View.VISIBLE
                            saveBtn.visibility = View.INVISIBLE
                            content.visibility = View.VISIBLE
                    }else{
                        Toast.makeText(activity, "수정하지 못했습니다.", Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    Toast.makeText(activity, "내용을 기입해 주세요.", Toast.LENGTH_LONG).show()
                    saveBtn.visibility = View.INVISIBLE
                }
        }
        return view
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