package com.example.healthapp.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.WorkBbsDto

// dto에 신고횟수 추가 필요
class AdapterBbs(private val context: Context, private val dataList: ArrayList<WorkBbsDto>)
    : RecyclerView.Adapter<AdapterBbs.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val abtitle = itemView.findViewById<TextView>(R.id.abTitle)
        private val abnick = itemView.findViewById<TextView>(R.id.abNick)
        private val abdate = itemView.findViewById<TextView>(R.id.abDate)
        private val abbad = itemView.findViewById<TextView>(R.id.abBad)

        fun bind(dto: WorkBbsDto, context: Context){
            abtitle.text = dto.title
            abnick.text = dto.nickname
            abdate.text = dto.wdate
//            abbad.text
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBbs.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.admin_bbs_layout, parent, false)
        return AdapterBbs.ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterBbs.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}