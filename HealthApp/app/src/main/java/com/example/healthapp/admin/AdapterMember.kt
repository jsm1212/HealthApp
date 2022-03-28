package com.example.healthapp.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.R

// 회원 신고수 추가되면 수정
class AdapterMember(private val context: Context, private val dataList: ArrayList<LoginMemberDto>)
    : RecyclerView.Adapter<AdapterMember.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val amid = itemView.findViewById<TextView>(R.id.amId)
        private val amnick = itemView.findViewById<TextView>(R.id.amNick)
        private val amname = itemView.findViewById<TextView>(R.id.amName)
        private val amemail = itemView.findViewById<TextView>(R.id.amEmail)
        private val amtel = itemView.findViewById<TextView>(R.id.amTel)
        private val amregi = itemView.findViewById<TextView>(R.id.amRegiDate)
        private val ambad = itemView.findViewById<TextView>(R.id.amBad)

        fun bind(dto: LoginMemberDto, context: Context){
            amid.text = dto.id
            amnick.text = dto.nickname
            amname.text = dto.name
            amemail.text = dto.email
            amtel.text = dto.tel
            amregi.text = dto.regidate
//            ambad.text = dto.wdate
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMember.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admin_member_layout, parent, false)
        return AdapterMember.ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterMember.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}