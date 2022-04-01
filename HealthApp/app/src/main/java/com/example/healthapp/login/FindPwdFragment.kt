package com.example.healthapp.login

import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.healthapp.R

class FindPwdFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_find_pwd_fragment, container, false)

        val findId = view.findViewById<EditText>(R.id.findIdEditText2)
        val findName = view.findViewById<EditText>(R.id.findNameEditText2)
        val findTel = view.findViewById<EditText>(R.id.findTelEditText2)

        val findPwdBtn = view.findViewById<Button>(R.id.findMyPwdBtn)

        findPwdBtn.setOnClickListener {
            val id = findId.text.toString().trim()
            val name = findName.text.toString().trim()
            val tel = findTel.text.toString().trim()

            val dto = LoginMemberDao.getInstance().findPwd_M(LoginMemberDto(id,"",name,"","",0,"",tel,0,"","", 0))

            if(dto != null){

                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(dto.tel,null,dto.pwd,null,null)

                Toast.makeText(container?.context, "문자로 비밀번호가 전송되었습니다.", Toast.LENGTH_LONG).show()

                val i = Intent(activity, LoginActivity::class.java)
                startActivity(i)
            }else {
                Toast.makeText(container?.context,"존재하지않는 회원정보입니다.", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }
}