package com.example.healthapp.bbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.healthapp.R
import com.example.healthapp.fragment.BbsFragment
import com.example.healthapp.fragment.CalendarFragment
import com.example.healthapp.fragment.MypageFragment
import com.example.healthapp.fragment.WorklistFragment
import com.example.healthapp.login.LoginMemberDao
import kotlin.system.exitProcess

class WorkActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var selectedFragment: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        getSupportActionBar()!!.setIcon(R.drawable.appbar)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setElevation(0F)

        val fm = supportFragmentManager

        val fragmentTransaction = fm.beginTransaction()

        if(selectedFragment == 0){
            fragmentTransaction.add(R.id.frView, WorklistFragment(this, applicationContext))
        }else if(selectedFragment == 1){
            fragmentTransaction.add(R.id.frView, BbsFragment(this))
        }else if(selectedFragment == 2){
            fragmentTransaction.add(R.id.frView, CalendarFragment(this))
        }else if(selectedFragment == 3){
            fragmentTransaction.add(R.id.frView, MypageFragment(this))
        }else{
            fragmentTransaction.add(R.id.frView, WorklistFragment(this, applicationContext))
        }
        fragmentTransaction.commit()

        // 버튼 숨기기
        val mypageBtn = findViewById<Button>(R.id.MyPageBtn)
        val adminBtn = findViewById<Button>(R.id.AdminBtn)
        val calBtn = findViewById<Button>(R.id.myCalendarBtn)

        mypageBtn.visibility = View.GONE
        adminBtn.visibility = View.GONE
        calBtn.visibility = View.GONE

        // 버튼 보여주기
        val auth = LoginMemberDao.user?.auth
        when(auth){
            // 관리자
            1 -> adminBtn.visibility = View.VISIBLE
            // 회원
            3, 4, 5 -> {
                mypageBtn.visibility = View.VISIBLE
                calBtn.visibility = View.VISIBLE
            }
        }
    }

    private var backPressedTime: Long = 0

    override fun onBackPressed() {
        println("뒤로가기버튼!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")

        if(System.currentTimeMillis() - backPressedTime >= 1500){
            // 처음 클릭 메시지
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.finishAffinity(this)
            System.runFinalization()
            exitProcess(0)
        }
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




























