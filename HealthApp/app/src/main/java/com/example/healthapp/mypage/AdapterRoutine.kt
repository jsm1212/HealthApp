package com.example.healthapp.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.WorkBbsDto

// dto 만들어지면 수정
class AdapterRoutine(private val context: Context, private val dataList: ArrayList<WorkBbsDto>)
    : RecyclerView.Adapter<AdapterRoutine.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val rtList = itemView.findViewById<TextView>(R.id.myrtList)
        private val rtCount = itemView.findViewById<TextView>(R.id.myrtCount)

        fun bind(dto: WorkBbsDto, context: Context){
            rtList.text = "오늘은 하체"
            rtCount.text = dto.bbsLike.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRoutine.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mypage_routine_layout, parent, false)
        return AdapterRoutine.ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterRoutine.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}