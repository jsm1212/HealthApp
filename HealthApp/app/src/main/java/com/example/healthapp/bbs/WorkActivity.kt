package com.example.healthapp.bbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.healthapp.R
import com.example.healthapp.fragment.BbsFragment
import com.example.healthapp.fragment.CalendarFragment
import com.example.healthapp.fragment.MypageFragment
import com.example.healthapp.fragment.WorklistFragment

class WorkActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

//        val btn1 = findViewById<Button>(R.id.goToBbs)
//        val btn2 = findViewById<Button>(R.id.goToMypage)
//        val btn3 = findViewById<Button>(R.id.goToAdmin)
//        val btn4 = findViewById<Button>(R.id.goToWork)

//        // 게시판으로 이동
//        btn1.setOnClickListener {
//            val intent = Intent(this, WorkBbsActivity::class.java)
//            startActivity(intent)
//        }
//        // 마이페이지
//        btn2.setOnClickListener {
//            val intent = Intent(this, MypageActivity::class.java)
//            startActivity(intent)
//        }
//        // 관리자페이지
//        btn3.setOnClickListener {
//            val intent = Intent(this, AdminActivity::class.java)
//            startActivity(intent)
//        }
//        // 운동목록
//        btn4.setOnClickListener {
//            val intent = Intent(this, WorklistActivity::class.java)
//            startActivity(intent)
//        }

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.frView, WorklistFragment(this, applicationContext))
        fragmentTransaction.commit()
    }

    override fun onClick(view: View?) {
        var fr:Fragment? = null

        if(view?.id == R.id.BbsListBtn){
            fr = BbsFragment(this)
        }else if(view?.id == R.id.workListBtn){
            fr = WorklistFragment(this, applicationContext)
        }else if(view?.id == R.id.MyPageBtn){
            fr = MypageFragment(this)
        }else if(view?.id == R.id.myCalendarBtn){
            fr = CalendarFragment(this)
        }

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frView, fr!!)
        fragmentTransaction.commit()
    }
}




























