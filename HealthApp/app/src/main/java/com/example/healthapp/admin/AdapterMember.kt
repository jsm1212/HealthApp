package com.example.healthapp.admin

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.login.LoginMemberDto
import com.example.healthapp.R
import com.example.healthapp.login.LoginActivity

class AdapterMember(private val context: Context, private val dataList: ArrayList<LoginMemberDto>)
:RecyclerView.Adapter<AdapterMember.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val amid = itemView.findViewById<TextView>(R.id.amId)
        private val amnick = itemView.findViewById<TextView>(R.id.amNick)
        private val amname = itemView.findViewById<TextView>(R.id.amName)
        private val amemail = itemView.findViewById<TextView>(R.id.amEmail)
        private val amtel = itemView.findViewById<TextView>(R.id.amTel)
        private val amregi = itemView.findViewById<TextView>(R.id.amRegiDate)

        @SuppressLint("ResourceAsColor")
        fun bind(dto: LoginMemberDto, context: Context){
            amid.text = dto.id
            amnick.text = dto.nickname
            amname.text = dto.name
            amemail.text = dto.email
            amtel.text = dto.tel
            amregi.text = dto.regidate

            if(dto.del == 1){
                itemView.setBackgroundColor(R.color.warn)
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