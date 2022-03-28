package com.example.healthapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.healthapp.CalendarDao
import com.example.healthapp.CalendarDto
import com.example.healthapp.R
import java.util.*

class CalendarFragment(val activity:Context) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val content = view.findViewById<EditText>(R.id.contextEditText)

        val date = view.findViewById<TextView>(R.id.diaryTextView)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)

        //날짜 설정하는부분 버전 오류뜨나 걍써도 상관없다 한다.
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR).toString() + "/"
        val month = (cal.get(Calendar.MONTH) + 1).toString() + "/"
        val day = cal.get(Calendar.DATE).toString()



        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 달력 날짜가 선택되면
            date.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            date.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            //title.setText("")
            content.setText("") // EditText에 공백값 넣기

     }
    val saveBtn = view.findViewById<Button>(R.id.save_Btn)
        saveBtn.setOnClickListener{
            Log.d("@@@@@@", "aaaaaaaaaa!!!!!!!!!!!!!")
           // val t = title.text.toString()
            //Log.d("@@@@@@",t)
            val c = content.text.toString()
            Log.d("@@@@@@",c)
            val msg = CalendarDao.getInstance().writeCalendar_M(CalendarDto(0,"",c,"",0,"id",year+month+day))
            if( msg != null) {
                Log.d("@@@@@에러", msg!!)
                if(msg == "YES") {
                    Toast.makeText(activity, "저장되었습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
        val chrBtn = view.findViewById<Button>(R.id.cha_Btn)
        chrBtn.setOnClickListener {
            val c = content.text.toString()
            val msg = CalendarDao.getInstance().updateCalendar(CalendarDto(0,"",c,"",0,"id",year+month+day))
            if( msg != null) {
                if(msg == "OK") {
                    Toast.makeText(activity, "수정되었습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
        return view
    }

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_calendar)
//       // val title = findViewById<EditText>(R.id.titleEditText)
//        val content = findViewById<EditText>(R.id.contextEditText)
//
//        val date = findViewById<TextView>(R.id.diaryTextView)
//
//        val calendarView = findViewById<CalendarView>(R.id.calendarView)
//
//        //날짜 설정하는부분 버전 오류뜨나 걍써도 상관없다 한다.
//        val cal = Calendar.getInstance()
//        val year = cal.get(Calendar.YEAR).toString() + "/"
//        val month = (cal.get(Calendar.MONTH) + 1).toString() + "/"
//        val day = cal.get(Calendar.DATE).toString()
//
//
//
//        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            // 달력 날짜가 선택되면
//            date.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
//            date.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
//            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
//            //title.setText("")
//            content.setText("") // EditText에 공백값 넣기
//
//     }
//    val saveBtn = findViewById<Button>(R.id.save_Btn)
//        saveBtn.setOnClickListener{
//            Log.d("@@@@@@", "aaaaaaaaaa!!!!!!!!!!!!!")
//           // val t = title.text.toString()
//            //Log.d("@@@@@@",t)
//            val c = content.text.toString()
//            Log.d("@@@@@@",c)
//            val msg = CalendarDao.getInstance().writeCalendar_M(CalendarDto(0,"",c,"",0,"id",year+month+day))
//            if( msg != null) {
//                Log.d("@@@@@에러", msg!!)
//                if(msg == "YES") {
//                    Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//        val chrBtn = findViewById<Button>(R.id.cha_Btn)
//        chrBtn.setOnClickListener {
//            val c = content.text.toString()
//            val msg = CalendarDao.getInstance().updateCalendar(CalendarDto(0,"",c,"",0,"id",year+month+day))
//            if( msg != null) {
//                if(msg == "OK") {
//                    Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }
}

