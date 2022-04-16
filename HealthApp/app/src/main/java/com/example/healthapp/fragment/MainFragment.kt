package com.example.healthapp.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.healthapp.R
import com.example.healthapp.bbs.WorkActivity
import com.example.healthapp.login.LoginMemberDao

class MainFragment : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        getSupportActionBar()!!.setIcon(R.drawable.appbar)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setElevation(0F)

        // 버튼 숨기기
        val mypageBtn = findViewById<ImageView>(R.id.MyPageBtn)
        val adminBtn = findViewById<ImageView>(R.id.AdminBtn)
        val calBtn = findViewById<ImageView>(R.id.myCalendarBtn)

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
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()

        if(WorkActivity.selectedFragment == 0){
            fragmentTransaction.add(R.id.frView, WorklistFragment(this, applicationContext))
        }else if(WorkActivity.selectedFragment == 1){
            fragmentTransaction.add(R.id.frView, BbsFragment(this))
        }else if(WorkActivity.selectedFragment == 2){
            fragmentTransaction.add(R.id.frView, CalendarFragment(this))
        }else if(WorkActivity.selectedFragment == 3){
            fragmentTransaction.add(R.id.frView, MypageFragment(this))
        }else{
            fragmentTransaction.add(R.id.frView, WorklistFragment(this, applicationContext))
        }
        fragmentTransaction.commit()
    }

    override fun onClick(view: View?) {
        var fr:Fragment? = null
        val ft = supportFragmentManager.beginTransaction()

        if(view?.id == R.id.BbsListBtn){
            fr = BbsFragment(this)
            R.id.BbsListBtn
        }else if(view?.id == R.id.workListBtn){
            fr = WorklistFragment(this, applicationContext)
        }else if(view?.id == R.id.MyPageBtn){
            fr = MypageFragment(this)
        }else if(view?.id == R.id.myCalendarBtn){
            fr = CalendarFragment(this)
        }else if(view?.id == R.id.AdminBtn){
            fr = AdminFragment(this)
        }
        ft.replace(R.id.frView, fr!!)
        ft.commit()
    }
}




























