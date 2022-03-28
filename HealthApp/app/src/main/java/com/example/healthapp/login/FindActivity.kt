package com.example.healthapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.healthapp.R

class FindActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.content, FindIdFragment())
        fragmentTransaction.commit()

    }

    override fun onClick(view: View?) {
        var fr:Fragment? = null

        if(view?.id == R.id.findIdBtn){
            fr = FindIdFragment()
        }
        else if(view?.id == R.id.findPwdBtn){
            fr = FindPwdFragment()
        }

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.content, fr!!)
        fragmentTransaction.commit()
    }
}