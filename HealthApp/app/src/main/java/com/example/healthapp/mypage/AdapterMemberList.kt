package com.example.healthapp.mypage

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.R
import kotlinx.android.synthetic.main.admin_member_layout.view.*

class AdapterMemberList(private val context: Context, private val dataList: ArrayList<LoginMemberDto>)
:RecyclerView.Adapter<AdapterMemberList.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val amid = itemView.findViewById<TextView>(R.id.amId)
        private val amnick = itemView.findViewById<TextView>(R.id.amNick)
        private val amname = itemView.findViewById<TextView>(R.id.amName)
        private val amemail = itemView.findViewById<TextView>(R.id.amEmail)
        private val amtel = itemView.findViewById<TextView>(R.id.amTel)
        private val amregi = itemView.findViewById<TextView>(R.id.amRegiDate)

        fun bind(dto: LoginMemberDto, context: Context){
            amid.text = dto.id
            amnick.text = dto.nickname
            amname.text = dto.name
            amemail.text = dto.email
            amtel.text = dto.tel
            amregi.text = dto.regidate

            itemView.amDeleteBtn.setOnClickListener{
                val id = dto.id
                println(id)

                // 회원 삭제
                AlertDialog.Builder(context.applicationContext)
                    .setTitle("삭제")
                    .setMessage("$id 님을 삭제하시겠습니까?")
                    .setCancelable(false)
                    .setNeutralButton("확인", DialogInterface.OnClickListener { _, _ ->
                        val data = MypageDao.getInstance().deleteMember(id!!)
                        println(data)
                    }).show()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admin_member_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}