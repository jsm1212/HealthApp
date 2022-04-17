package com.example.healthapp.mypage

import android.content.Context
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.login.LoginMemberDto
import kotlinx.android.synthetic.main.admin_recycle.view.*


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
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admin_recycle, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)

        // 회원삭제 제발...
        val idx = dataList[position]
        val deleteId = idx.id
        holder.itemView.setOnClickListener{
            MypageDao.getInstance().deleteMember(deleteId!!)
        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}