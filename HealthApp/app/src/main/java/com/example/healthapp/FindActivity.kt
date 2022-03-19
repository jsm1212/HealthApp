package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

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

//            val findName = findViewById<EditText>(R.id.findNameEditText)
//            val findTel = findViewById<EditText>(R.id.findTelEditText)
//
//            val findIdBtn = findViewById<Button>(R.id.findMyIdBtn)
//
//            findIdBtn.setOnClickListener {
//                val name = findName.text.toString().trim()
//                val tel = findTel.text.toString().trim()
//
//                val dto = LoginMemberDao.getInstance().findId_M(LoginMemberDto("","",name,"","",0,"",tel,0,""))
//
//                if(dto != null){
//
//                    Toast.makeText(this, "문자로 비밀번호가 전송되었습니다.", Toast.LENGTH_LONG).show()
//                    Log.d("@@@@@findId",dto.pwd!!)
//
//                    val i = Intent(this, LoginActivity::class.java)
//                    startActivity(i)
//                }else {
//                    Toast.makeText(this,"존재하지않는 회원정보입니다.", Toast.LENGTH_LONG).show()
//                }
//            }
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