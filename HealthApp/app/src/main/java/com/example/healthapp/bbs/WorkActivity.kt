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

        val fm = supportFragmentManager

        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.frView, WorklistFragment(this, applicationContext))
        fragmentTransaction.commit()

//        if(index == null || index == 1){
//            fragmentTransaction.add(R.id.frView, WorklistFragment(WorkActivity(1), applicationContext))
//        }else if(index != null && index == 2){
//            fragmentTransaction.add(R.id.frView, BbsFragment(WorkActivity(2)))
//        }else if(index != null && index == 3){
//            fragmentTransaction.add(R.id.frView, CalendarFragment(WorkActivity(3)))
//        }else if(index != null && index == 4){
//            fragmentTransaction.add(R.id.frView, MypageFragment(WorkActivity(4)))
//        }else{
//            fragmentTransaction.add(R.id.frView, WorklistFragment(WorkActivity(1), applicationContext))
//        }
//        fragmentTransaction.commit()
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




























