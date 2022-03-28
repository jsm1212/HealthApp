package com.example.healthapp.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.WorkBbsDto

class AdapterReply(private val context: Context, private val dataList: ArrayList<WorkBbsDto>)
    : RecyclerView.Adapter<AdapterReply.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val rpreply = itemView.findViewById<TextView>(R.id.myrpReply)
        private val rptitle = itemView.findViewById<TextView>(R.id.myrpTitle)
        private val rpdate = itemView.findViewById<TextView>(R.id.myrpDate)

        fun bind(dto: WorkBbsDto, context: Context){
            rpreply.text = "댓글입니다\n댓글입니다\n댓글입니다" // 댓글은 어디에...?
            rptitle.text = dto.title
            rpdate.text = dto.wdate
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterReply.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_reply_layout, parent, false)
        return AdapterReply.ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterReply.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}