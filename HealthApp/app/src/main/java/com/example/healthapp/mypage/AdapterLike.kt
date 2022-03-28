package com.example.healthapp.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.workbbs.WorkBbsDto

class AdapterLike(private val context: Context, private val dataList: ArrayList<WorkBbsDto>)
    : RecyclerView.Adapter<AdapterLike.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val ltitle = itemView.findViewById<TextView>(R.id.mylTitle)
        private val lperson = itemView.findViewById<TextView>(R.id.mylPerson)
        private val ldate = itemView.findViewById<TextView>(R.id.mylDate)

        fun bind(dto: WorkBbsDto, context: Context){
            ltitle.text = dto.title
            lperson.text = dto.nickname
            ldate.text = dto.wdate
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLike.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_like_layout, parent, false)
        return AdapterLike.ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterLike.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}