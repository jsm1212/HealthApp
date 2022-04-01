package com.example.healthapp.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.healthapp.R

class FindIdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_find_id_fragment, container, false)

        val findName = view.findViewById<EditText>(R.id.findNameEditText)
        val findTel = view.findViewById<EditText>(R.id.findTelEditText)

        val findIdBtn = view.findViewById<Button>(R.id.findMyIdBtn)

        findIdBtn.setOnClickListener {
            val name = findName.text.toString().trim()
            val tel = findTel.text.toString().trim()

            val dto = LoginMemberDao.getInstance().findId_M(LoginMemberDto("","",name,"","",0,"",tel,0,"","", 0))

            if(dto != null){
                    Toast.makeText(container?.context, "문자로 아이디가 전송되었습니다.", Toast.LENGTH_LONG).show()
                    Log.d("@@@@@findId", dto.id!!)
                    val i = Intent(activity, LoginActivity::class.java)
                    startActivity(i)
            }else {
                Toast.makeText(container?.context,"존재하지않는 회원정보입니다.", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

}